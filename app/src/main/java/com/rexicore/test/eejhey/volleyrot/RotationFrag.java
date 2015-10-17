package com.rexicore.test.eejhey.volleyrot;


import android.content.Intent;
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
public class RotationFrag extends Fragment implements View.OnClickListener {

    private TextView tvPos1, tvPos2, tvPos3, tvPos4, tvPos5, tvPos6;
    private Button rotCW, rotCCW, modify;
    protected static final String PREFS_NAME = "Prefs";
    private SharedPreferences sp;

    public RotationFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate( R.layout.fragment_rotation, container, false );

        sp = this.getActivity().getSharedPreferences( PREFS_NAME, 0 );

        tvPos1 = (TextView) view.findViewById(R.id.tvPos1);
        tvPos2 = (TextView) view.findViewById(R.id.tvPos2);
        tvPos3 = (TextView) view.findViewById(R.id.tvPos3);
        tvPos4 = (TextView) view.findViewById(R.id.tvPos4);
        tvPos5 = (TextView) view.findViewById(R.id.tvPos5);
        tvPos6 = (TextView) view.findViewById(R.id.tvPos6);

        rotCW = (Button) view.findViewById(R.id.bRotateCW);
        rotCCW = (Button) view.findViewById(R.id.bRotateCCW);
        modify = (Button) view.findViewById(R.id.bEditPlayers);

        rotCW.setOnClickListener(this);
        rotCCW.setOnClickListener(this);
        modify.setOnClickListener(this);

        loadPrefs();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        loadPrefs();
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.bRotateCW:
                String tmp = tvPos6.getText().toString();
                tvPos6.setText(tvPos1.getText().toString());
                tvPos1.setText(tvPos2.getText().toString());
                tvPos2.setText(tvPos3.getText().toString());
                tvPos3.setText(tvPos4.getText().toString());
                tvPos4.setText(tvPos5.getText().toString());
                tvPos5.setText(tmp);
                savePrefs();
                break;
            case R.id.bRotateCCW:
                String tmp2 = tvPos6.getText().toString();
                tvPos6.setText(tvPos5.getText().toString());
                tvPos5.setText(tvPos4.getText().toString());
                tvPos4.setText(tvPos3.getText().toString());
                tvPos3.setText(tvPos2.getText().toString());
                tvPos2.setText(tvPos1.getText().toString());
                tvPos1.setText(tmp2);
                savePrefs();
                break;
            /*case R.id.bEditPlayers:
                Intent intent = new Intent(getActivity(), EditPlayers.class);
                getActivity().startActivity(intent);
                break;*/
        }
    }

    private void loadPrefs(){
        tvPos1.setText(sp.getString("POS1", "1"));
        tvPos2.setText(sp.getString("POS2", "2"));
        tvPos3.setText(sp.getString("POS3", "3"));
        tvPos4.setText(sp.getString("POS4", "4"));
        tvPos5.setText(sp.getString("POS5", "5"));
        tvPos6.setText(sp.getString("POS6", "6"));
    }

    private void savePrefs(){
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("POS1", tvPos1.getText().toString());
        editor.putString("POS2", tvPos2.getText().toString());
        editor.putString("POS3", tvPos3.getText().toString());
        editor.putString("POS4", tvPos4.getText().toString());
        editor.putString("POS5", tvPos5.getText().toString());
        editor.putString("POS6", tvPos6.getText().toString());
        editor.commit();
    }
}
