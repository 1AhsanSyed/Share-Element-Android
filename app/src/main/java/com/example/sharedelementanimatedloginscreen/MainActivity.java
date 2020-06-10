package com.example.sharedelementanimatedloginscreen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.util.Pair;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.profileImage)
    CircleImageView profileImage;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.designation)
    TextView designation;
    @BindView(R.id.cardview)
    CardView cardview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getWindow().setSharedElementEnterTransition(enterTransition());
        getWindow().setSharedElementReturnTransition(returnTransition());

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent shareIntent = new Intent(MainActivity.this,SharedElementActivity.class);

                Pair[] pairs = new Pair[4];
                pairs[0] = new Pair<View,String>(profileImage,"profilePicture");
                pairs[1] = new Pair<View,String>(name,"animationName");
                pairs[2] = new Pair<View,String>(email,"animationEmail");
                pairs[3] = new Pair<View,String>(designation,"animationDesignation");

                ActivityOptions shareOption = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                startActivity(shareIntent,shareOption.toBundle());

            }
        });


    }


    private Transition enterTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setDuration(500);

        return bounds;
    }

    private Transition returnTransition() {
        ChangeBounds bounds = new ChangeBounds();
        bounds.setInterpolator(new DecelerateInterpolator());
        bounds.setDuration(500);

        return bounds;
    }
}