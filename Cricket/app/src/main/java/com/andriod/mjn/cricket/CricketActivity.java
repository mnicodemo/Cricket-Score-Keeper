package com.andriod.mjn.cricket;

import android.os.Bundle;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

      public class CricketActivity extends AppCompatActivity implements ScoreFragment.Callbacks, GameOverFragment.Callbacks   {
//    public class CricketActivity extends AppCompatActivity implements ScoreFragment.Callbacks  {

    private String mWinnerName = " ";
    //private static final int REQUEST_GAMEOVER = 0;
    private boolean mGameIsOver = false;

    private PlayerFragment pf = new PlayerFragment();
    private ScoreFragment sf20 = new ScoreFragment(20);
    private ScoreFragment sf19 = new ScoreFragment(19);
    private ScoreFragment sf18 = new ScoreFragment(18);
    private ScoreFragment sf17 = new ScoreFragment(17);
    private ScoreFragment sf16 = new ScoreFragment(16);
    private ScoreFragment sf15 = new ScoreFragment(15);
    private ScoreFragment sfBull = new ScoreFragment(25);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cricket);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

/*        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/


/*        ScoreFragment SF20 = new ScoreFragment("20");
        ScoreFragment SF19 = new ScoreFragment("19");
        ScoreFragment SF18 = new ScoreFragment("18");*/
        FragmentManager manager=getSupportFragmentManager();//create an instance of fragment manager
        FragmentTransaction transaction=manager.beginTransaction();//create an instance of Fragment-transaction

        transaction.add(R.id.FragPlayer, pf);
        transaction.add(R.id.Frag20, sf20);
        transaction.add(R.id.Frag19, sf19);
        transaction.add(R.id.Frag18, sf18);
        transaction.add(R.id.Frag17, sf17);
        transaction.add(R.id.Frag16, sf16);
        transaction.add(R.id.Frag15, sf15);
        transaction.add(R.id.FragBull, sfBull);

        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cricket, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onScoreChanged()
    {
        int p1Score = 0;
        int p2Score = 0;

        p1Score = sf20.getM_P1ScoreAmt() +
                sf19.getM_P1ScoreAmt() +
                sf18.getM_P1ScoreAmt() +
                sf17.getM_P1ScoreAmt() +
                sf16.getM_P1ScoreAmt() +
                sf15.getM_P1ScoreAmt() +
                sfBull.getM_P1ScoreAmt();

        p2Score = sf20.getM_P2ScoreAmt() +
                sf19.getM_P2ScoreAmt() +
                sf18.getM_P2ScoreAmt() +
                sf17.getM_P2ScoreAmt() +
                sf16.getM_P2ScoreAmt() +
                sf15.getM_P2ScoreAmt() +
                sfBull.getM_P2ScoreAmt();

        pf.setPlayerScore1(p1Score);
        pf.setPlayerScore2(p2Score);
        if(!mGameIsOver)  //if the game is over we are resetting values to replay - don't call IsGameOver again
            isGameOver();
    }
    @Override
    public void isGameOver()
    {
        if(mGameIsOver)  //leave this routine if the game is already over
            return;
        // check to see if the game is over
        if(sf20.isP1AreAllChecked() && sf19.isP1AreAllChecked() && sf18.isP1AreAllChecked()&&
                sf17.isP1AreAllChecked() && sf16.isP1AreAllChecked() && sf15.isP1AreAllChecked() && sfBull.isP1AreAllChecked())
        {
            //game is over - determine winner and show a dialog
            //if Player 1's score is equal too or higher then P2's then the game is over
            //onScoreChanged(); //make sure that the current score is calculated
            if(pf.getPlayerScore1()>=pf.getPlayerScore2())
            {
                mWinnerName = pf.getET_PlayerName1().getText().toString();
                FragmentManager manager = getSupportFragmentManager();
                GameOverFragment dialog = GameOverFragment.newInstance(mWinnerName);
                dialog.show(manager, "GameOverFragment");
                mGameIsOver = true;
                return;
            }
        }

        if(sf20.isP2AreAllChecked() && sf19.isP2AreAllChecked() && sf18.isP2AreAllChecked()&&
                sf17.isP2AreAllChecked() && sf16.isP2AreAllChecked() && sf15.isP2AreAllChecked() && sfBull.isP2AreAllChecked())
        {
            //game is over - determine winner and show a dialog

            //if Player 2's score is equal too or higher then P1's then the game is over
           // onScoreChanged(); //make sure that the current score is calculated
            if(pf.getPlayerScore2()>=pf.getPlayerScore1()) {
                mWinnerName = pf.getET_PlayerName2().getText().toString();
                FragmentManager manager = getSupportFragmentManager();
                GameOverFragment dialog = GameOverFragment.newInstance(mWinnerName);
               // dialog.setTargetFragment(manager, REQUEST_GAMEOVER);
                dialog.show(manager, "GameOverFragment");
                mGameIsOver = true;
                return;
            }

        }
    }

    public void ResetAndReplay()
    {
        sf20.ResetAll();
        sf19.ResetAll();
        sf18.ResetAll();
        sf17.ResetAll();
        sf16.ResetAll();
        sf15.ResetAll();
        sfBull.ResetAll();
        onScoreChanged(); //reset the player score information now that everything is uncecked and zeroed out
        mGameIsOver = false;
    }
    public void Quit()
    {
        System.exit(0);
    }

    public String getWinnerName() {
        return mWinnerName;
    }

}
