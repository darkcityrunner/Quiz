package com.example.android.iqquiz2;

import android.app.Dialog;
import android.content.Intent;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity implements LogicalQuestions.logicalQuestionsInterface,
        MathematicalQuestions.mathematicalQuestionsInterface,
        VerbalQuestions.verbalQuestionsInterface
        {

    private static final String TAG = "MainActivity";

    /* FRAGMENT INTERFACES */
    //Logical questions fragment
    @Override
    public void sendLogicalQuestions(String logic1Input, String logic2Input, String logic3Input, String logic4Input, String logic5Input) {
        push_LogicalQuestion1 = logic1Input;
        push_LogicalQuestion2 = logic2Input;
        push_LogicalQuestion3 = logic3Input;
        push_LogicalQuestion4 = logic4Input;
        push_LogicalQuestion5 = logic5Input;
    }

    //Mathematical questions fragment
    @Override
    public void sendMathematicalQuestions(String math1Input, String math2Input, String math3Input, String math4Input, String math5Input) {
        push_MathematicalQuestion1 = math1Input;
        push_MathematicalQuestion2 = math2Input;
        push_MathematicalQuestion3 = math3Input;
        push_MathematicalQuestion4 = math4Input;
        push_MathematicalQuestion5 = math5Input;
    }

     //Verbal questions fragment
     @Override
     public void sendVerbalQuestions(String verbal1Input, String verbal2Input, String verbal3Input, String verbal4Input1, String verbal4Input2, String verbal4Input3, String verbal4Input4, String verbal5Input) {
         push_VerbalQuestion1 = verbal1Input;
         push_VerbalQuestion2 = verbal2Input;
         push_VerbalQuestion3 = verbal3Input;
         push_VerbalQuestion4_box1 = verbal4Input1;
         push_VerbalQuestion4_box2 = verbal4Input2;
         push_VerbalQuestion4_box3 = verbal4Input3;
         push_VerbalQuestion4_box4 = verbal4Input4;
         push_VerbalQuestion5 = verbal5Input;
     }

    /* GLOBAL VARIABLE DECLARATIONS */

    Dialog goBackToSplashPageDialog;

    private PagerAdapter mPagerAdapter;
    private ViewPager mViewPager;
    private ImageButton goToSplashPageButton;
    private ImageButton goToResultsPageButton;

    private Chronometer timer;
    private boolean timerRunning;
    private long capturedTimerValue;
    private String convertedTimerValue;

    public String push_LogicalQuestion1;
    public String push_LogicalQuestion2;
    public String push_LogicalQuestion3;
    public String push_LogicalQuestion4;
    public String push_LogicalQuestion5;

    public String push_MathematicalQuestion1;
    public String push_MathematicalQuestion2;
    public String push_MathematicalQuestion3;
    public String push_MathematicalQuestion4;
    public String push_MathematicalQuestion5;

    public String push_VerbalQuestion1;
    public String push_VerbalQuestion2;
    public String push_VerbalQuestion3;
    public String push_VerbalQuestion4_box1;
    public String push_VerbalQuestion4_box2;
    public String push_VerbalQuestion4_box3;
    public String push_VerbalQuestion4_box4;
    public String push_VerbalQuestion5;

    boolean errorFlag;

    /* ON CREATE */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goBackToSplashPageDialog = new Dialog(this);

        // SECTION: PAGER ADAPTER AND TAB LAYOUT
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(mViewPager);

        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(mViewPager);

        // SECTION: TIMER FUNCTIONALITY (ON CREATE)
        timer = (Chronometer) findViewById(R.id.timer);
        timerRunning=false;
        boolean quizTimer = getIntent().getExtras().getBoolean("quizTimer");

        if (!quizTimer) {
            timer.setVisibility(View.GONE); //hides the timer view that's defined in the xml if the user has opted to take the untimed quiz
        }
        else  {
            timer.setBase(SystemClock.elapsedRealtime()); //sets the timer start time to the current time
            timer.start(); //starts the timer
            timerRunning=true; //sets a flag so the program will know if the timer is running when the users clicks to go to the results page
        }

        // SECTION: RETURN TO THE SPLASH PAGE (ON CLICK LISTENER)
        goToSplashPageButton = (ImageButton)findViewById(R.id.goToSplashPage); //sets the button variable to the imageview button in the xml
        goToSplashPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGoBackToSplashPageDialog(view); //calls the method to open up the dialog window
            }
        });

        // SECTION: GO TO THE RESULTS PAGE (ON CLICK LISTENER)
        goToResultsPageButton = (ImageButton)findViewById(R.id.goToResultsPage);
        goToResultsPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //VALIDATE THAT THE USER HAS ANSWERED ALL QUESTIONS (IF NO, DISPLAY TOAST AND DO NOT PROCEED TO RESULTS PAGE)
                errorFlag = false;

                if(TextUtils.isEmpty(push_LogicalQuestion1)) {
                    errorFlag = true;
                }
                if(TextUtils.isEmpty(push_MathematicalQuestion1)){
                    errorFlag = true;
                }
                if(TextUtils.isEmpty(push_VerbalQuestion1)){
                    errorFlag = true;
                }
                if (errorFlag == true) {
                    LayoutInflater inflater = getLayoutInflater();
                    View toastLinearLayout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

                    TextView toastText = toastLinearLayout.findViewById(R.id.toast_message_TextView);
                    toastText.setText(R.string.answerEveryQuestionToastMessage);

                    Toast toast = new Toast(getApplicationContext()); //Use getApplicationContext here because we're in an activity
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(toastLinearLayout); //Use the toastLinearLayout view we declared a few lines above because we're in an activity
                    toast.show();
                    errorFlag=false;
                    return;
                }

                //TIMER FUNCTIONALITY (GO TO RESULTS PAGE LOGIC)
                if(timerRunning){
                    timer.stop(); //the timer continues to run, but the display will stop updating
                    capturedTimerValue = SystemClock.elapsedRealtime() - timer.getBase(); //captures the timer value which is in milliseconds by subtracting the current time from when the timer started
                    convertedTimerValue = (new SimpleDateFormat("mm:ss").format(capturedTimerValue)); //converts the timer value to a string in mm:ss format
                    timerRunning=false;
                }
                else {
                    convertedTimerValue="00:00";
                }

                //INTENT: OPEN AND SEND DATA TO RESULTS PAGE
                Intent pushToResultsIntent = new Intent(MainActivity.this, ResultsPage.class);

                //PACK DATA TO RESULTS PAGE
                pushToResultsIntent.putExtra("timerValue", convertedTimerValue);
                pushToResultsIntent.putExtra("logicalQuestion1", push_LogicalQuestion1);
                pushToResultsIntent.putExtra("logicalQuestion2", push_LogicalQuestion2);
                pushToResultsIntent.putExtra("logicalQuestion3", push_LogicalQuestion3);
                pushToResultsIntent.putExtra("logicalQuestion4", push_LogicalQuestion4);
                pushToResultsIntent.putExtra("logicalQuestion5", push_LogicalQuestion5);
                pushToResultsIntent.putExtra("mathematicalQuestion1", push_MathematicalQuestion1);
                pushToResultsIntent.putExtra("mathematicalQuestion2", push_MathematicalQuestion2);
                pushToResultsIntent.putExtra("mathematicalQuestion3", push_MathematicalQuestion3);
                pushToResultsIntent.putExtra("mathematicalQuestion4", push_MathematicalQuestion4);
                pushToResultsIntent.putExtra("mathematicalQuestion5", push_MathematicalQuestion5);
                pushToResultsIntent.putExtra("verbalQuestion1", push_VerbalQuestion1);
                pushToResultsIntent.putExtra("verbalQuestion2", push_VerbalQuestion2);
                pushToResultsIntent.putExtra("verbalQuestion3", push_VerbalQuestion3);
                pushToResultsIntent.putExtra("verbalQuestion4_box1", push_VerbalQuestion4_box1);
                pushToResultsIntent.putExtra("verbalQuestion4_box2", push_VerbalQuestion4_box2);
                pushToResultsIntent.putExtra("verbalQuestion4_box3", push_VerbalQuestion4_box3);
                pushToResultsIntent.putExtra("verbalQuestion4_box4", push_VerbalQuestion4_box4);
                pushToResultsIntent.putExtra("verbalQuestion5", push_VerbalQuestion5);

                startActivity(pushToResultsIntent);
            }
        });
    }

    /* PAGER ADAPTER */
    private void setupViewPager(ViewPager viewPager){
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new LogicalQuestions(), "Logical"); //adds the title "Logical" to the first tab
        adapter.addFragment(new MathematicalQuestions(), "Mathematical");
        adapter.addFragment(new VerbalQuestions(), "Verbal");
        viewPager.setAdapter(adapter);
    }

    /* GO BACK TO SPLASH PAGE METHOD */
    public void showGoBackToSplashPageDialog(View view) {
        TextView yesButtonWithinGoBackDialog; //this is a local variable for the yes button
        TextView cancelButtonWithinGoBackDialog; //this is a local variable for the cancel button

        goBackToSplashPageDialog.setContentView(R.layout.activity_go_to_splash_page_dialog); //this sets the dialog activity to the dialog variable
        TextView goBackTextView = (TextView) goBackToSplashPageDialog.findViewById(R.id.goBackToSplashPageTextView);
        goBackTextView.setText(R.string.goToSplashPageDialogTextFromQuiz);

        goBackToSplashPageDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); //This will hide the background that's in the dialog layout xml file so the rounded cardview corners will show
        goBackToSplashPageDialog.show(); //this opens the confirmation dialog asking is the user wants to return to the splash page

        yesButtonWithinGoBackDialog = (TextView) goBackToSplashPageDialog.findViewById(R.id.goBackToSplashPage); //sets the local variable to the yes button
        yesButtonWithinGoBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(MainActivity.this, SplashPage.class); //opens the splash page
                startActivity(intent);
                goBackToSplashPageDialog.dismiss(); //closes the dialog
            }
        });

        cancelButtonWithinGoBackDialog = (TextView) goBackToSplashPageDialog.findViewById(R.id.closeGoBackToSplashPageDialog); //sets the local variable to the cancel button
        cancelButtonWithinGoBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                goBackToSplashPageDialog.dismiss(); //if the user clicks cancel, the dialog will be closed
            }
        });
    }

    /* SAVE INSTANCE STATE */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState); // Always call the superclass so it can save the view hierarchy state

        savedInstanceState.putString("logic1", push_LogicalQuestion1);
        savedInstanceState.putString("logic2", push_LogicalQuestion2);
        savedInstanceState.putString("logic3", push_LogicalQuestion3);
        savedInstanceState.putString("logic4", push_LogicalQuestion4);
        savedInstanceState.putString("logic5", push_LogicalQuestion5);

        savedInstanceState.putString("math1", push_MathematicalQuestion1);
        savedInstanceState.putString("math2", push_MathematicalQuestion2);
        savedInstanceState.putString("math3", push_MathematicalQuestion3);
        savedInstanceState.putString("math4", push_MathematicalQuestion4);
        savedInstanceState.putString("math5", push_MathematicalQuestion5);

        savedInstanceState.putString("verbal1", push_VerbalQuestion1);
        savedInstanceState.putString("verbal2", push_VerbalQuestion2);
        savedInstanceState.putString("verbal3", push_VerbalQuestion3);
        savedInstanceState.putString("verbal41", push_VerbalQuestion4_box1);
        savedInstanceState.putString("verbal42", push_VerbalQuestion4_box2);
        savedInstanceState.putString("verbal43", push_VerbalQuestion4_box3);
        savedInstanceState.putString("verbal44", push_VerbalQuestion4_box4);
        savedInstanceState.putString("verbal5", push_VerbalQuestion5);

        if(timerRunning){
            capturedTimerValue = SystemClock.elapsedRealtime() - timer.getBase(); //captures the timer value which is in milliseconds by subtracting the current time from when the timer started
            savedInstanceState.putLong("timerValue", capturedTimerValue);
            savedInstanceState.putBoolean("timerRunning", timerRunning);
        }
    }

    /* RESTORE INSTANCE STATE */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        push_LogicalQuestion1 = savedInstanceState.getString("logic1");
        push_LogicalQuestion2 = savedInstanceState.getString("logic2");
        push_LogicalQuestion3 = savedInstanceState.getString("logic3");
        push_LogicalQuestion4 = savedInstanceState.getString("logic4");
        push_LogicalQuestion5 = savedInstanceState.getString("logic5");

        push_MathematicalQuestion1 = savedInstanceState.getString("math1");
        push_MathematicalQuestion2 = savedInstanceState.getString("math2");
        push_MathematicalQuestion3 = savedInstanceState.getString("math3");
        push_MathematicalQuestion4 = savedInstanceState.getString("math4");
        push_MathematicalQuestion5 = savedInstanceState.getString("math5");

        push_VerbalQuestion1 = savedInstanceState.getString("verbal1");
        push_VerbalQuestion2 = savedInstanceState.getString("verbal2");
        push_VerbalQuestion3 = savedInstanceState.getString("verbal3");
        push_VerbalQuestion4_box1 = savedInstanceState.getString("verbal41");
        push_VerbalQuestion4_box2 = savedInstanceState.getString("verbal42");
        push_VerbalQuestion4_box3 = savedInstanceState.getString("verbal43");
        push_VerbalQuestion4_box4 = savedInstanceState.getString("verbal44");
        push_VerbalQuestion5 = savedInstanceState.getString("verbal5");

        capturedTimerValue = savedInstanceState.getLong("timerValue");
        timerRunning = savedInstanceState.getBoolean("timerRunning");

        if(timerRunning){
            timer.setBase(SystemClock.elapsedRealtime() - capturedTimerValue);
            timer.start();
        }
    }
}

