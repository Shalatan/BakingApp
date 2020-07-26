package com.example.bakingtutorialapp.Fragments;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bakingtutorialapp.POJO.StepsPOJO;
import com.example.bakingtutorialapp.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;

import java.util.ArrayList;

public class DetailFragment extends Fragment {
    private String mLong;
    private String mShort;
    private String mVideo;
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private boolean playWhenReady = true;
    private long playbackPosition = 0;

    private int currentWindow = 0;
    Activity activity;
    private ArrayList<StepsPOJO> mStepList;
    private int position;

    public DetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mStepList = getArguments().getParcelableArrayList("stepList");
        position = getArguments().getInt("position");
        mShort = mStepList.get(position).getmShortDescription();
        mLong = mStepList.get(position).getmLongDescription();
        mVideo = mStepList.get(position).getmVideoUrl();
        activity = this.getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail_part, container, false);
        long newPosition = 0;
        if (savedInstanceState != null) {
            newPosition = savedInstanceState.getLong("POS");
            playWhenReady = savedInstanceState.getBoolean("STATE");
        }


        final TextView shortView = rootView.findViewById(R.id.detailFragmentShortStep);
        final TextView longView = rootView.findViewById(R.id.detailFragmentLongStep);
        Button nextStep = rootView.findViewById(R.id.detailFragmentNextStepButton);
        playerView = rootView.findViewById(R.id.video_view);


        startFragment(shortView, longView, newPosition, playWhenReady);

        nextStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position++;
                if (position == mStepList.size()) {
                    position = 0;
                }
                mShort = mStepList.get(position).getmShortDescription();
                mLong = mStepList.get(position).getmLongDescription();
                mVideo = mStepList.get(position).getmVideoUrl();
                startFragment(shortView, longView, 0, true);
            }
        });
        return rootView;
    }

    private void startFragment(TextView shortView, TextView longView, long position, boolean state) {
        shortView.setText(mShort);
        longView.setText(mLong);
        Uri uri = Uri.parse(mVideo);
        MediaSource mediaSource = buildMediaSource(uri);
        player = ExoPlayerFactory.newSimpleInstance(activity);
        playerView.setPlayer(player);
        player.setPlayWhenReady(state);
        player.prepare(mediaSource);
        player.seekTo(position);
    }

    private MediaSource buildMediaSource(Uri uri) {
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(activity, "exoplayer");
        return new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(uri);
    }


    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("POS", playbackPosition);
        outState.putBoolean("STATE", playWhenReady);
    }
}
