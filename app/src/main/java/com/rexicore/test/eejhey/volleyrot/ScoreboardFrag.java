package com.rexicore.test.eejhey.volleyrot;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreboardFrag extends Fragment implements View.OnClickListener {
    private TextView tvTheirScore, tvOurScore;
    private Button bTheyAdd, bTheySubtract, bWeAdd, bWeSubtract, bTheyReset, bWeReset;
    private SharedPreferences sp;
    private final String PREFS_NAME = "Score";

    public ScoreboardFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scoreboard, container, false);

        sp = getActivity().getSharedPreferences(PREFS_NAME, 0);

        tvTheirScore = (TextView) view.findViewById(R.id.tvThemScore);
        tvOurScore = (TextView) view.findViewById(R.id.tvUsScore);
        bTheyAdd = (Button) view.findViewById(R.id.bThemAdd);
        bTheySubtract = (Button) view.findViewById(R.id.bThemMinus);
        bWeAdd = (Button) view.findViewById(R.id.bUsAdd);
        bWeSubtract = (Button) view.findViewById(R.id.bUsMinus);
        bTheyReset = (Button) view.findViewById(R.id.bThemReset);
        bWeReset = (Button) view.findViewById(R.id.bUsReset);

        bTheyAdd.setOnClickListener(this);
        bTheySubtract.setOnClickListener(this);
        bWeAdd.setOnClickListener(this);
        bWeSubtract.setOnClickListener(this);
        bTheyReset.setOnClickListener(this);
        bWeReset.setOnClickListener(this);

        loadPrefs();

        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bThemAdd:
                tvTheirScore.setText(newScore(tvTheirScore.getText().toString(), "+"));
                break;
            case R.id.bThemMinus:
                tvTheirScore.setText(newScore(tvTheirScore.getText().toString(), "-"));
                break;
            case R.id.bUsAdd:
                tvOurScore.setText(newScore(tvOurScore.getText().toString(), "+"));
                break;
            case R.id.bUsMinus:
                tvOurScore.setText(newScore(tvOurScore.getText().toString(), "-"));
                break;
            case R.id.bThemReset:
                tvTheirScore.setText("00");
                break;
            case R.id.bUsReset:
                tvOurScore.setText("00");
                break;
        }
        savePrefs();
    }

    private String newScore(String oldScore, String mAddSub) {
        String mScoreOut = "00";
        int score, newScore = 0;

        if (mAddSub == "+") {
            score = Integer.parseInt(oldScore);
            newScore = score + 1;
        } else {
            score = Integer.parseInt(oldScore);
            if (score > 0)
                newScore = score - 1;
        }

        mScoreOut = String.valueOf(newScore);
        if (newScore < 10) {
            mScoreOut = "0" + mScoreOut;
        }

        return mScoreOut;
    }

    private void loadPrefs() {
        tvTheirScore.setText(sp.getString("Them", "00"));
        tvOurScore.setText(sp.getString("Us", "00"));
    }

    private void savePrefs() {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("Them", tvTheirScore.getText().toString());
        editor.putString("Us", tvOurScore.getText().toString());
        editor.commit();
    }
}
