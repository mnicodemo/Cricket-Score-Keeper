package com.andriod.mjn.cricket;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import java.nio.BufferUnderflowException;
import java.sql.Statement;

/**
 * Created by mjn on 2/6/2016.
 */
public class GameOverFragment extends DialogFragment {

    private Callbacks mCallbacks;
    private String WinnerName = " ";
    private static final String WINNER_NAME = "Winner_name";
    private static final int REQUEST_GAMEOVER = 0;



    public static GameOverFragment newInstance(String WinnerName)
    {
        Bundle args = new Bundle();
        args.putSerializable(WINNER_NAME, WinnerName);
        GameOverFragment fragment = new GameOverFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public interface Callbacks
    {
        void Quit();
        void ResetAndReplay();
    }
    @Override
    public  void onAttach(Context context)
    {
        super.onAttach(context);
        mCallbacks = (Callbacks) context;
    }
    @Override
    public void onDetach()
    {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        String WinnerName = (String) getArguments().getSerializable(WINNER_NAME);

        String WinnerMessage = getString( R.string.the_winner_is);
        WinnerMessage = WinnerMessage + " " + WinnerName;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(WinnerMessage).setPositiveButton(R.string.reset_and_replay, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Call Back to Clear and re-start
                Toast.makeText(getContext(), "reset and re=pplay", Toast.LENGTH_SHORT).show();
                mCallbacks.ResetAndReplay();
            }
        }).setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Call Back Quit Application
                Toast.makeText(getContext(), "quit", Toast.LENGTH_SHORT).show();
                mCallbacks.Quit();
            }
        });
        return builder.create();
    }

}
