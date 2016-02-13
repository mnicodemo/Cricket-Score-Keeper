package com.andriod.mjn.cricket;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by mjn on 1/30/2016.
 */
public class PlayerFragment extends Fragment
{


    private View mView;
    private EditText mET_PlayerName1;
    private TextView mTV_PlayerScore1;
    private EditText mET_PlayerName2;
    private TextView mTV_PlayerScore2;

    private int mPlayerScore1 = 0;
    private int mPlayerScore2 = 0;



    public PlayerFragment()
    {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mView = inflater.inflate(R.layout.player_fragment, container, false);
        mET_PlayerName1 = (EditText) mView.findViewById(R.id.PlayerName_1);
        mET_PlayerName2 = (EditText) mView.findViewById(R.id.PlayerName_2);

        mTV_PlayerScore1 = (TextView) mView.findViewById(R.id.PlayerScore_1);
        mTV_PlayerScore2 = (TextView) mView.findViewById(R.id.PlayerScore_2);

        return mView;
    }

    public int getPlayerScore1() {
        return mPlayerScore1;
    }

    public void setPlayerScore1(int playerScore1) {
        mPlayerScore1 = playerScore1;
        setTV_PlayerScore1();
    }

    public int getPlayerScore2() {
        return mPlayerScore2;
    }

    public void setPlayerScore2(int playerScore2) {
        mPlayerScore2 = playerScore2;
        setTV_PlayerScore2();
    }

    public void setTV_PlayerScore1() {
        mTV_PlayerScore1.setText(Integer.toString(mPlayerScore1));
    }

    public void setTV_PlayerScore2() {
        mTV_PlayerScore2.setText(Integer.toString(mPlayerScore2));
    }

    public EditText getET_PlayerName1() {
        return mET_PlayerName1;
    }

    public EditText getET_PlayerName2() {
        return mET_PlayerName2;
    }

}
