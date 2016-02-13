package com.andriod.mjn.cricket;


import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mjn on 1/30/2016.
 */
public class ScoreFragment extends Fragment
{
    private static final String SCORE_MULTIPLIER = "SCORE_MULTIPLIER";

    private Callbacks mCallbacks;

    private String mScoreText = " ";
    private int mScoreMultiplyer = 0;
    private int m_P1ScoreAmt = 0;
    private int m_P2ScoreAmt = 0;

    private int mP1NbrToAddToScore = 0;
    private int mP2NbrToAddToScore = 0;

    private View mView;

    private LinearLayout mLinearLayoutId;
    private CheckBox mCBP1_1;
    private CheckBox mCBP1_2;
    private CheckBox mCBP1_3;

    private CheckBox mCBP2_1;
    private CheckBox mCBP2_2;
    private CheckBox mCBP2_3;


    private ImageButton mIBtn_P1Plus;
    private ImageButton mIBtn_P1Minus;
    private ImageButton mIBtn_P2Plus;
    private ImageButton mIBtn_P2Minus;

    private EditText mET_P1NbrAdditional;
    private EditText mET_P2NbrAdditional;

    private boolean mP1AreAllChecked = false;
    private boolean mP2AreAllChecked = false;

    /**
     * Required interface for hosting activities
     */
    public interface Callbacks
    {
        void onScoreChanged();
        void isGameOver();
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
/*    public static ScoreFragment newInstance(int ScoreMultiplier)
    {
        Bundle args = new Bundle();
        args.putSerializable(SCORE_MULTIPLIER, ScoreMultiplier);
        loadMultiplier(ScoreMultiplier);


        ScoreFragment fragment = new ScoreFragment();
        fragment.setArguments(args);
        return fragment;
    }*/

    public ScoreFragment()
    {

    }
    public ScoreFragment(int iScore) {

       // setScoreMultiplyer(iScore);
        mScoreMultiplyer = iScore;
        if (iScore == 25) {
            mScoreText = "Bull";
        } else
        {
            mScoreText =  Integer.toString(iScore);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        mView = inflater.inflate(R.layout.score_fragment, container, false);
        TextView tv = (TextView) mView.findViewById(R.id.TVScoreAmt);
        tv.setText(mScoreText);

        mLinearLayoutId = (LinearLayout) mView.findViewById(R.id.LinearLayoutId);
        int remainder = mScoreMultiplyer % 2;
        //if(remainder == 0)
        //    mLinearLayoutId.setBackgroundColor(#AFAFAF);

        mCBP1_1 = (CheckBox) mView.findViewById(R.id.cbP1_1);
        mCBP1_1.setOnCheckedChangeListener(P1OnCheckedChangedListener);

        mCBP1_2 = (CheckBox) mView.findViewById(R.id.cbP1_2);
        mCBP1_2.setOnCheckedChangeListener(P1OnCheckedChangedListener);

        mCBP1_3 = (CheckBox) mView.findViewById(R.id.cbP1_3);
        mCBP1_3.setOnCheckedChangeListener(P1OnCheckedChangedListener);



        mCBP2_1 = (CheckBox) mView.findViewById(R.id.cbP2_1);
        mCBP2_1.setOnCheckedChangeListener(P2OnCheckedChangedListener);

        mCBP2_2 = (CheckBox) mView.findViewById(R.id.cbP2_2);
        mCBP2_2.setOnCheckedChangeListener(P2OnCheckedChangedListener);

        mCBP2_3 = (CheckBox) mView.findViewById(R.id.cbP2_3);
        mCBP2_3.setOnCheckedChangeListener(P2OnCheckedChangedListener);

        mIBtn_P1Plus = (ImageButton)mView.findViewById((R.id.btnplus_p1));
        mIBtn_P1Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add 1 to the edit text and score
                mP1NbrToAddToScore++;
                String sAmt = Integer.toString(mP1NbrToAddToScore);
                mET_P1NbrAdditional.setText(sAmt);
            }
        });
        mIBtn_P1Minus = (ImageButton)mView.findViewById((R.id.btnminus_p1));
        mIBtn_P1Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Subtract 1 to the edit text and score
                if(mP1NbrToAddToScore==0)
                    return;

                mP1NbrToAddToScore--;
                String sAmt = Integer.toString(mP1NbrToAddToScore);
                mET_P1NbrAdditional.setText(sAmt);
            }
        });

        mIBtn_P2Plus = (ImageButton)mView.findViewById((R.id.btnplus_p2));
        mIBtn_P2Plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Add 1 to the edit text and score
                mP2NbrToAddToScore++;
                String sAmt = Integer.toString(mP2NbrToAddToScore);
                mET_P2NbrAdditional.setText(sAmt);

            }
        });
        mIBtn_P2Minus = (ImageButton)mView.findViewById((R.id.btnminus_p2));
        mIBtn_P2Minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Subtract 1 to the edit text and score
                if(mP2NbrToAddToScore==0)
                    return;

                mP2NbrToAddToScore--;
                String sAmt = Integer.toString(mP2NbrToAddToScore);
                mET_P2NbrAdditional.setText(sAmt);
            }
        });


        mET_P1NbrAdditional = (EditText) mView.findViewById(R.id.edP1NbrAddition);
        mET_P1NbrAdditional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sI = s.toString();

              //  if(sI == null)

                try
                {
                    mP1NbrToAddToScore = Integer.parseInt(sI);
                } catch (NumberFormatException n)
                {
                    //do nothing - it's ok as we default the amount to zero
                }
                SetScoreP1();

            }

            @Override
            public void afterTextChanged(Editable s) {
/*                String sI = s.toString();
                int amt = Integer.parseInt(sI);
                m_P1ScoreAmt = amt * mScoreMultiplyer;*/
            }
        });
        mET_P1NbrAdditional.setEnabled(false);

        mET_P2NbrAdditional = (EditText) mView.findViewById(R.id.edP2NbrAddition);
        mET_P2NbrAdditional.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String sI = s.toString();
                try
                {
                    mP2NbrToAddToScore = Integer.parseInt(sI);
                } catch (NumberFormatException n)
                {
                    //do nothing - it's ok as we default the amount to zero
                }
                SetScoreP2();

            }

            @Override
            public void afterTextChanged(Editable s) {
/*                String sI = s.toString();
                int amt = Integer.parseInt(sI);
                m_P2ScoreAmt = amt * mScoreMultiplyer;*/
            }
        });
        mET_P2NbrAdditional.setEnabled(false);

        return mView;
    }
    private void SetScoreP1()
    {
        m_P1ScoreAmt = mP1NbrToAddToScore * mScoreMultiplyer;
        mCallbacks.onScoreChanged(); //tell the main activity to change the score
    }
    private void SetScoreP2()
    {
        m_P2ScoreAmt = mP2NbrToAddToScore * mScoreMultiplyer;
        mCallbacks.onScoreChanged(); //tell the main activity to change the score
    }


    private void CheckBoxReview()
    {
        mP1AreAllChecked = AreAllP1Checked();
        mP2AreAllChecked = AreAllP2Checked();

        if (mP1AreAllChecked && !mP2AreAllChecked)   //P1 finished P2 Not finished - enable P1
        {
            mET_P1NbrAdditional.setEnabled(true);
            mIBtn_P1Plus.setVisibility(View.VISIBLE);
            mIBtn_P1Minus.setVisibility(View.VISIBLE);

            mET_P2NbrAdditional.setEnabled(false);
            mIBtn_P2Plus.setVisibility(View.INVISIBLE);
            mIBtn_P2Minus.setVisibility(View.INVISIBLE);
        } else if (mP1AreAllChecked && mP2AreAllChecked)  //P1 & P2 are both finished - disable both
        {
            mET_P1NbrAdditional.setEnabled(false);
            mET_P2NbrAdditional.setEnabled(false);

            mIBtn_P1Plus.setVisibility(View.INVISIBLE);
            mIBtn_P1Minus.setVisibility(View.INVISIBLE);
            mIBtn_P2Plus.setVisibility(View.INVISIBLE);
            mIBtn_P2Minus.setVisibility(View.INVISIBLE);
        } else if(!mP1AreAllChecked && mP2AreAllChecked)  // P1 not finished P2 is finished - Enable P2
        {
            mET_P1NbrAdditional.setEnabled(false);
            mIBtn_P1Plus.setVisibility(View.INVISIBLE);
            mIBtn_P1Minus.setVisibility(View.INVISIBLE);

            mET_P2NbrAdditional.setEnabled(true);
            mIBtn_P2Plus.setVisibility(View.VISIBLE);
            mIBtn_P2Minus.setVisibility(View.VISIBLE);
        }
        else //otherwise disable both
        {
            mET_P1NbrAdditional.setEnabled(false);
            mET_P2NbrAdditional.setEnabled(false);

            mIBtn_P1Plus.setVisibility(View.INVISIBLE);
            mIBtn_P1Minus.setVisibility(View.INVISIBLE);
            mIBtn_P2Plus.setVisibility(View.INVISIBLE);
            mIBtn_P2Minus.setVisibility(View.INVISIBLE);
        }
    }
    private CompoundButton.OnCheckedChangeListener P1OnCheckedChangedListener = new CompoundButton.OnCheckedChangeListener()
    { @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {
            //check if all P2's are set and any p1's are not - if so enable Edit otherwise disable it
            m_P1ScoreAmt = 0;  //since we have a change in checkboxes it must be zero
            mET_P1NbrAdditional.setText(" ");
            mP1NbrToAddToScore = 0;
            SetScoreP1();


            CheckBoxReview();  //call the CheckBoxreview for player 1
            if(mP1AreAllChecked)
                mCallbacks.isGameOver();  //leverage main activity to check if the game is over
        }

    };
    private CompoundButton.OnCheckedChangeListener P2OnCheckedChangedListener = new CompoundButton.OnCheckedChangeListener()
    { @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
        {

            m_P2ScoreAmt = 0;  //since we have a change in checkboxes it must be zero
            mET_P2NbrAdditional.setText("");
            mP2NbrToAddToScore = 0;
            SetScoreP2();

            CheckBoxReview();  //call the CheckBoxreview for player 2
            if(mP2AreAllChecked)
                mCallbacks.isGameOver();  //leverage main activity to check if the game is over

        }

    };

    private boolean AreAllP1Checked()
    {
        if(mCBP1_1.isChecked() && mCBP1_2.isChecked() && mCBP1_3.isChecked())
            return true;
        else
            return false;
    }
    private boolean AreAllP2Checked()
    {
        if(mCBP2_1.isChecked() && mCBP2_2.isChecked() && mCBP2_3.isChecked())
            return true;
        else
            return false;
    }
    public View getFragmentView()
    {
        return mView;
    }

    public int getScoreMultiplyer() {
        return mScoreMultiplyer;
    }

    public void setScoreMultiplyer(int scoreMultiplyer) {
        mScoreMultiplyer = scoreMultiplyer;
    }

    public int getM_P1ScoreAmt() {
        return m_P1ScoreAmt;
    }

    public int getM_P2ScoreAmt() {
        return m_P2ScoreAmt;
    }

    public CheckBox getCBP1_1() {
        return mCBP1_1;
    }

    public CheckBox getCBP1_2() {
        return mCBP1_2;
    }

    public CheckBox getCBP1_3() {
        return mCBP1_3;
    }

    public boolean isP1AreAllChecked() {
        return mP1AreAllChecked;
    }
    public boolean isP2AreAllChecked() {
        return mP2AreAllChecked;
    }



    public void ResetAll()
    {
        mScoreText = " ";
        mScoreMultiplyer = 0;
        m_P1ScoreAmt = 0;
        m_P2ScoreAmt = 0;
        mCBP1_1.setChecked(false);
        mCBP1_2.setChecked(false);
        mCBP1_3.setChecked(false);

        mCBP2_1.setChecked(false);
        mCBP2_2.setChecked(false);
        mCBP2_3.setChecked(false);


        mET_P1NbrAdditional.setText(" ");
        mET_P2NbrAdditional.setText(" ");

        mP1AreAllChecked = false;
        mP2AreAllChecked = false;
    }
}
