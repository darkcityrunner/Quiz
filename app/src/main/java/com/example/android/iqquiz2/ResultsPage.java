package com.example.android.iqquiz2;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ResultsPage extends AppCompatActivity {

    Dialog goBackToSplashPageDialog;
    private ImageButton goToSplashPageButton;

    /* GLOBAL VARIABLE DECLARATIONS */
    // SECTION: QUESTIONS USER INPUT VARIABLES
    // Logical questions variables
    TextView logicalQuestion1_UserAnswer_PushToTextView, logicalQuestion1_CorrectAnswer_PushToTextView;
    TextView logicalQuestion2_UserAnswer_PushToTextView, logicalQuestion2_CorrectAnswer_PushToTextView;
    TextView logicalQuestion3_UserAnswer_PushToTextView, logicalQuestion3_CorrectAnswer_PushToTextView;
    TextView logicalQuestion4_UserAnswer_PushToTextView, logicalQuestion4_CorrectAnswer_PushToTextView;
    TextView logicalQuestion5_UserAnswer_PushToTextView, logicalQuestion5_CorrectAnswer_PushToTextView;

    // Mathematical questions variables
    TextView mathematicalQuestion1_UserAnswer_PushToTextView, mathematicalQuestion1_CorrectAnswer_PushToTextView;
    TextView mathematicalQuestion2_UserAnswer_PushToTextView, mathematicalQuestion2_CorrectAnswer_PushToTextView;
    TextView mathematicalQuestion3_UserAnswer_PushToTextView, mathematicalQuestion3_CorrectAnswer_PushToTextView;
    TextView mathematicalQuestion4_UserAnswer_PushToTextView, mathematicalQuestion4_CorrectAnswer_PushToTextView;
    TextView mathematicalQuestion5_UserAnswer_PushToTextView, mathematicalQuestion5_CorrectAnswer_PushToTextView;

    // Verbal questions variables
    TextView verbalQuestion1_UserAnswer_PushToTextView, verbalQuestion1_CorrectAnswer_PushToTextView;
    TextView verbalQuestion2_UserAnswer_PushToTextView, verbalQuestion2_CorrectAnswer_PushToTextView;
    TextView verbalQuestion3_UserAnswer_PushToTextView, verbalQuestion3_CorrectAnswer_PushToTextView;
    TextView verbalQuestion4_UserAnswer_PushToTextView, verbalQuestion4_CorrectAnswer_PushToTextView;
    TextView verbalQuestion5_UserAnswer_PushToTextView, verbalQuestion5_CorrectAnswer_PushToTextView;

    // QUESTIONS ANSWER VARIABLES
    String logicalQuestion1_CorrectAnswer = "855"; //Add 1, multiply 1, add 2, multiply 2, and so on.
    String logicalQuestion2_CorrectAnswer = "4"; //4 tricycles
    String logicalQuestion3_CorrectAnswer = "66 percent"; //
    String logicalQuestion4_CorrectAnswer = "They are the same.";
    String logicalQuestion5_CorrectAnswer = "$1.00";
    String mathematicalQuestion1_CorrectAnswer = "150"; //
    String mathematicalQuestion2_CorrectAnswer = "303"; //100 / .33 = 303
    String mathematicalQuestion3_CorrectAnswer = "Cube B would hold more."; //
    String mathematicalQuestion4_CorrectAnswer = "4";
    String mathematicalQuestion5_CorrectAnswer = "18"; //60 minutes divided by 10 minutes = 6. 6 times 3 stations = 18 stations.
    String verbalQuestion1_CorrectAnswer = "23";
    String verbalQuestion2_CorrectAnswer = "FIST";
    String verbalQuestion3_CorrectAnswer = "Identical";
    String verbalQuestion4_CorrectAnswer1 = "Imperfect";
    String verbalQuestion4_CorrectAnswer2 = "Faulty";
    String verbalQuestion5_CorrectAnswer = "No";

    String logicalQuestion1_Explanation = "Add 1, multiply 1, add 2, multiply 2, and so on.";
    String mathematicalQuestion5_Explanation = "60 minutes divided by 10 minutes = 6. 6 times 3 stations = 18 stations.";

    String verbalQ4_UserInput_Concatenated;
    String verbalQ4_CorrectAnswer_Concatenated;

    // SECTION: SCORE KEEPING VARIABLES
    int overallScore;
    int logicalScore;
    int mathScore;
    int verbalScore;
    TextView overallScoreTextView;
    TextView logicalScoreTextView;
    TextView mathScoreTextView;
    TextView verbalScoreTextView;

    // SECTION: TIMER VARIABLES
    TextView timerTextView;
    TextView timerHeaderTextView;

    /* ON CREATE */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        //SECTION: Timer Value
        timerTextView = (TextView) findViewById(R.id.timerDisplay);
        timerHeaderTextView = (TextView) findViewById(R.id.timerHeader);
        String timerValue = (getIntent().getExtras().getString("timerValue"));
        timerTextView.setText(getIntent().getExtras().getString("timerValue"));

        if (timerValue.equals("00:00")){
            timerTextView.setVisibility(View.INVISIBLE); //hides the timer view that's defined in the xml if the user has opted to take the untimed quiz
            timerHeaderTextView.setVisibility(View.INVISIBLE); //hides the timer view that's defined in the xml if the user has opted to take the untimed quiz
        }

        //SECTION: Set findviewbyid
        overallScoreTextView = (TextView) findViewById(R.id.overallQuestionsScoreTextView);
        logicalScoreTextView = (TextView) findViewById(R.id.logicalQuestionsScoreTextView);
        mathScoreTextView = (TextView) findViewById(R.id.mathQuestionsScoreTextView);
        verbalScoreTextView = (TextView) findViewById(R.id.verbalQuestionsScoreTextView);

        //SECTION: POPULATE USER INPUTS AND ANSWERS

        //INITIALIZE SCORE KEEPING VARIABLES
        overallScore = 0;
        logicalScore = 0;
        mathScore = 0;
        verbalScore = 0;

        //Section: LOGICAL QUESTIONS

        Intent intent = getIntent();

        //Logical Question 1
        logicalQuestion1_UserAnswer_PushToTextView = (TextView) findViewById(R.id.logicalQuestion1_UserAnswer_TextView);
        String logicalQuestion1_UserAnswer = intent.getStringExtra("logicalQuestion1");
        logicalQuestion1_UserAnswer_PushToTextView.setText("Your answer: " + logicalQuestion1_UserAnswer);

        logicalQuestion1_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.logicalquestion1_CorrectAnswer_TextView);
        logicalQuestion1_CorrectAnswer_PushToTextView.setText("Correct answer: " + logicalQuestion1_CorrectAnswer);

        LinearLayout logicalQ1Background = (LinearLayout) findViewById(R.id.logicalQuestion1_LinearLayout);
        if (logicalQuestion1_UserAnswer.equals(logicalQuestion1_CorrectAnswer)){
            logicalQ1Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyLogicScore();
        } else {
            logicalQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Logical Question 2
        logicalQuestion2_UserAnswer_PushToTextView = (TextView) findViewById(R.id.logicalQuestion2_UserAnswer_TextView);
        String logicalQuestion2_UserAnswer = intent.getStringExtra("logicalQuestion2");
        logicalQuestion2_UserAnswer_PushToTextView.setText("Your answer: " + logicalQuestion2_UserAnswer);

        logicalQuestion2_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.logicalquestion2_CorrectAnswer_TextView);
        logicalQuestion2_CorrectAnswer_PushToTextView.setText("Correct answer: " + logicalQuestion2_CorrectAnswer);

        LinearLayout logicalQ2Background = (LinearLayout) findViewById(R.id.logicalQuestion2_LinearLayout);
        if (logicalQuestion2_UserAnswer.equals(logicalQuestion2_CorrectAnswer)){
            logicalQ2Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyLogicScore();
        } else {
            logicalQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Logical Question 3
        logicalQuestion3_UserAnswer_PushToTextView = (TextView) findViewById(R.id.logicalQuestion3_UserAnswer_TextView);
        String logicalQuestion3_UserAnswer = intent.getStringExtra("logicalQuestion3");
        logicalQuestion3_UserAnswer_PushToTextView.setText("Your answer: " + logicalQuestion3_UserAnswer);

        logicalQuestion3_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.logicalquestion3_CorrectAnswer_TextView);
        logicalQuestion3_CorrectAnswer_PushToTextView.setText("Correct answer: " + logicalQuestion3_CorrectAnswer);

        LinearLayout logicalQ3Background = (LinearLayout) findViewById(R.id.logicalQuestion3_LinearLayout);
        if (logicalQuestion3_UserAnswer.equals(logicalQuestion3_CorrectAnswer)){
            logicalQ3Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyLogicScore();
        } else {
            logicalQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Logical Question 4
        logicalQuestion4_UserAnswer_PushToTextView = (TextView) findViewById(R.id.logicalQuestion4_UserAnswer_TextView);
        String logicalQuestion4_UserAnswer = intent.getStringExtra("logicalQuestion4");
        logicalQuestion4_UserAnswer_PushToTextView.setText("Your answer: " + logicalQuestion4_UserAnswer);

        logicalQuestion4_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.logicalquestion4_CorrectAnswer_TextView);
        logicalQuestion4_CorrectAnswer_PushToTextView.setText("Correct answer: " + logicalQuestion4_CorrectAnswer);

        LinearLayout logicalQ4Background = (LinearLayout) findViewById(R.id.logicalQuestion4_LinearLayout);
        if (logicalQuestion4_UserAnswer.equals(logicalQuestion4_CorrectAnswer)){
            logicalQ4Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyLogicScore();
        } else {
            logicalQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Logical Question 5
        logicalQuestion5_UserAnswer_PushToTextView = (TextView) findViewById(R.id.logicalQuestion5_UserAnswer_TextView);
            String logicalQuestion5_UserAnswer = intent.getStringExtra("logicalQuestion5");
            logicalQuestion5_UserAnswer_PushToTextView.setText("Your answer: " + logicalQuestion5_UserAnswer);

            logicalQuestion5_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.logicalquestion5_CorrectAnswer_TextView);
            logicalQuestion5_CorrectAnswer_PushToTextView.setText("Correct answer: " + logicalQuestion5_CorrectAnswer);

            LinearLayout logicalQ5Background = (LinearLayout) findViewById(R.id.logicalQuestion5_LinearLayout);
            if (logicalQuestion5_UserAnswer.equals(logicalQuestion5_CorrectAnswer)){
                logicalQ5Background.setBackgroundResource(R.drawable.cardview_border_green);
                tallyLogicScore();
            } else {
            logicalQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Section: MATHEMATICAL QUESTIONS

        //Mathematical Question 1
        mathematicalQuestion1_UserAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalQuestion1_UserAnswer_TextView);
        String mathematicalQuestion1_UserAnswer = intent.getStringExtra("mathematicalQuestion1");
        mathematicalQuestion1_UserAnswer_PushToTextView.setText("Your answer: " + mathematicalQuestion1_UserAnswer);

        mathematicalQuestion1_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalquestion1_CorrectAnswer_TextView);
        mathematicalQuestion1_CorrectAnswer_PushToTextView.setText("Correct answer: " + mathematicalQuestion1_CorrectAnswer);

        LinearLayout mathematicalQ1Background = (LinearLayout) findViewById(R.id.mathematicalQuestion1_LinearLayout);
        if (mathematicalQuestion1_UserAnswer.equals(mathematicalQuestion1_CorrectAnswer)){
            mathematicalQ1Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyMathScore();
        } else {
            mathematicalQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Mathematical Question 2
        mathematicalQuestion2_UserAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalQuestion2_UserAnswer_TextView);
        String mathematicalQuestion2_UserAnswer = intent.getStringExtra("mathematicalQuestion2");
        mathematicalQuestion2_UserAnswer_PushToTextView.setText("Your answer: " + mathematicalQuestion2_UserAnswer);

        mathematicalQuestion2_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalquestion2_CorrectAnswer_TextView);
        mathematicalQuestion2_CorrectAnswer_PushToTextView.setText("Correct answer: " + mathematicalQuestion2_CorrectAnswer);

        LinearLayout mathematicalQ2Background = (LinearLayout) findViewById(R.id.mathematicalQuestion2_LinearLayout);
        if (mathematicalQuestion2_UserAnswer.equals(mathematicalQuestion2_CorrectAnswer)){
            mathematicalQ2Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyMathScore();
        } else {
            mathematicalQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Mathematical Question 3
        mathematicalQuestion3_UserAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalQuestion3_UserAnswer_TextView);
        String mathematicalQuestion3_UserAnswer = intent.getStringExtra("mathematicalQuestion3");
        mathematicalQuestion3_UserAnswer_PushToTextView.setText("Your answer: " + mathematicalQuestion3_UserAnswer);

        mathematicalQuestion3_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalquestion3_CorrectAnswer_TextView);
        mathematicalQuestion3_CorrectAnswer_PushToTextView.setText("Correct answer: " + mathematicalQuestion3_CorrectAnswer);

        LinearLayout mathematicalQ3Background = (LinearLayout) findViewById(R.id.mathematicalQuestion3_LinearLayout);
        if (mathematicalQuestion3_UserAnswer.equals(mathematicalQuestion3_CorrectAnswer)){
            mathematicalQ3Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyMathScore();
        } else {
            mathematicalQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Mathematical Question 4
        mathematicalQuestion4_UserAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalQuestion4_UserAnswer_TextView);
        String mathematicalQuestion4_UserAnswer = intent.getStringExtra("mathematicalQuestion4");
        mathematicalQuestion4_UserAnswer_PushToTextView.setText("Your answer: " + mathematicalQuestion4_UserAnswer);

        mathematicalQuestion4_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalquestion4_CorrectAnswer_TextView);
        mathematicalQuestion4_CorrectAnswer_PushToTextView.setText("Correct answer: " + mathematicalQuestion4_CorrectAnswer);

        LinearLayout mathematicalQ4Background = (LinearLayout) findViewById(R.id.mathematicalQuestion4_LinearLayout);
        if (mathematicalQuestion4_UserAnswer.equals(mathematicalQuestion4_CorrectAnswer)){
            mathematicalQ4Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyMathScore();
        } else {
            mathematicalQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Mathematical Question 5
        mathematicalQuestion5_UserAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalQuestion5_UserAnswer_TextView);
        String mathematicalQuestion5_UserAnswer = intent.getStringExtra("mathematicalQuestion5");
        mathematicalQuestion5_UserAnswer_PushToTextView.setText("Your answer: " + mathematicalQuestion5_UserAnswer);

        mathematicalQuestion5_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.mathematicalquestion5_CorrectAnswer_TextView);
        mathematicalQuestion5_CorrectAnswer_PushToTextView.setText("Correct answer: " + mathematicalQuestion5_CorrectAnswer);

        LinearLayout mathematicalQ5Background = (LinearLayout) findViewById(R.id.mathematicalQuestion5_LinearLayout);
        if (mathematicalQuestion5_UserAnswer.equals(mathematicalQuestion5_CorrectAnswer)){
            mathematicalQ5Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyMathScore();
        } else {
            mathematicalQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Section: VERBAL QUESTIONS

        //Verbal Question 1
        verbalQuestion1_UserAnswer_PushToTextView = (TextView) findViewById(R.id.verbalQuestion1_UserAnswer_TextView);
        String verbalQuestion1_UserAnswer = intent.getStringExtra("verbalQuestion1");
        verbalQuestion1_UserAnswer_PushToTextView.setText("Your answer: " + verbalQuestion1_UserAnswer);

        verbalQuestion1_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.verbalquestion1_CorrectAnswer_TextView);
        verbalQuestion1_CorrectAnswer_PushToTextView.setText("Correct answer: " + verbalQuestion1_CorrectAnswer);

        LinearLayout verbalQ1Background = (LinearLayout) findViewById(R.id.verbalQuestion1_LinearLayout);
        if (verbalQuestion1_UserAnswer.equals(verbalQuestion1_CorrectAnswer)){
            verbalQ1Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyVerbalScore();
        } else {
            verbalQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Verbal Question 2
        verbalQuestion2_UserAnswer_PushToTextView = (TextView) findViewById(R.id.verbalQuestion2_UserAnswer_TextView);
        String verbalQuestion2_UserAnswer = intent.getStringExtra("verbalQuestion2");
        verbalQuestion2_UserAnswer_PushToTextView.setText("Your answer: " + verbalQuestion2_UserAnswer);

        verbalQuestion2_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.verbalquestion2_CorrectAnswer_TextView);
        verbalQuestion2_CorrectAnswer_PushToTextView.setText("Correct answer: " + verbalQuestion2_CorrectAnswer);

        LinearLayout verbalQ2Background = (LinearLayout) findViewById(R.id.verbalQuestion2_LinearLayout);
        if (verbalQuestion2_UserAnswer.equals(verbalQuestion2_CorrectAnswer)){
            verbalQ2Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyVerbalScore();
        } else {
            verbalQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Verbal Question 3
        verbalQuestion3_UserAnswer_PushToTextView = (TextView) findViewById(R.id.verbalQuestion3_UserAnswer_TextView);
        String verbalQuestion3_UserAnswer = intent.getStringExtra("verbalQuestion3");
        verbalQuestion3_UserAnswer_PushToTextView.setText("Your answer: " + verbalQuestion3_UserAnswer);

        verbalQuestion3_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.verbalquestion3_CorrectAnswer_TextView);
        verbalQuestion3_CorrectAnswer_PushToTextView.setText("Correct answer: " + verbalQuestion3_CorrectAnswer);

        LinearLayout verbalQ3Background = (LinearLayout) findViewById(R.id.verbalQuestion3_LinearLayout);
        if (verbalQuestion3_UserAnswer.equals(verbalQuestion3_CorrectAnswer)){
            verbalQ3Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyVerbalScore();
        } else {
            verbalQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Verbal Question 4
        verbalQuestion4_UserAnswer_PushToTextView = (TextView) findViewById(R.id.verbalQuestion4_UserAnswer_TextView);
        String verbalQuestion4_UserAnswer_Box1 = intent.getStringExtra("verbalQuestion4_box1");
        String verbalQuestion4_UserAnswer_Box2 = intent.getStringExtra("verbalQuestion4_box2");
        String verbalQuestion4_UserAnswer_Box3 = intent.getStringExtra("verbalQuestion4_box3");
        String verbalQuestion4_UserAnswer_Box4 = intent.getStringExtra("verbalQuestion4_box4");

        verbalQ4_UserInput_Concatenated = "";
        verbalQ4_UserInput_Concatenated = verbalQuestion4_UserAnswer_Box1 + verbalQuestion4_UserAnswer_Box2 + verbalQuestion4_UserAnswer_Box3 + verbalQuestion4_UserAnswer_Box4;
        verbalQ4_CorrectAnswer_Concatenated = "";
        verbalQ4_CorrectAnswer_Concatenated = verbalQuestion4_CorrectAnswer1 + verbalQuestion4_CorrectAnswer2;

        verbalQuestion4_UserAnswer_PushToTextView.setText("Your answer: " + verbalQ4_UserInput_Concatenated);

        verbalQuestion4_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.verbalquestion4_CorrectAnswer_TextView);
        verbalQuestion4_CorrectAnswer_PushToTextView.setText("Correct answer: " + verbalQ4_CorrectAnswer_Concatenated);

        LinearLayout verbalQ4Background = (LinearLayout) findViewById(R.id.verbalQuestion4_LinearLayout);
        if (verbalQ4_UserInput_Concatenated.equals(verbalQ4_CorrectAnswer_Concatenated)){
            verbalQ4Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyVerbalScore();
        } else {
            verbalQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        //Verbal Question 5
        verbalQuestion5_UserAnswer_PushToTextView = (TextView) findViewById(R.id.verbalQuestion5_UserAnswer_TextView);
        String verbalQuestion5_UserAnswer = intent.getStringExtra("verbalQuestion5");
        verbalQuestion5_UserAnswer_PushToTextView.setText("Your answer: " + verbalQuestion5_UserAnswer);

        verbalQuestion5_CorrectAnswer_PushToTextView = (TextView) findViewById(R.id.verbalquestion5_CorrectAnswer_TextView);
        verbalQuestion5_CorrectAnswer_PushToTextView.setText("Correct answer: " + verbalQuestion5_CorrectAnswer);

        LinearLayout verbalQ5Background = (LinearLayout) findViewById(R.id.verbalQuestion5_LinearLayout);
        if (verbalQuestion5_UserAnswer.equals(verbalQuestion5_CorrectAnswer)){
            verbalQ5Background.setBackgroundResource(R.drawable.cardview_border_green);
            tallyVerbalScore();
        } else {
            verbalQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
        }

        int overallPercentage = Math.round(100*overallScore/15);
        int logicalPercentage = Math.round(100*logicalScore/5);
        int mathPercentage = Math.round(100*mathScore/5);
        int verbalPercentage = Math.round(100*verbalScore/5);

        overallScoreTextView.setText(String.valueOf(overallScore + " of 15 | " + overallPercentage + "%"));
        logicalScoreTextView.setText(String.valueOf(logicalScore + " of 5 | " + logicalPercentage + "%"));
        mathScoreTextView.setText(String.valueOf(mathScore + " of 5 | " + mathPercentage + "%"));
        verbalScoreTextView.setText(String.valueOf(verbalScore + " of 5 | " + verbalPercentage + "%"));

        //Section: Perfect score toast
        if (overallScore == 15) {
            LayoutInflater inflater = getLayoutInflater();
            View toastLinearLayout = inflater.inflate(R.layout.toast_perfect_score, (ViewGroup) findViewById(R.id.toast_perfectScore_root));

            ImageView toastImage = toastLinearLayout.findViewById(R.id.toast_perfectScore_ImageView);
            toastImage.setImageResource(R.drawable.perfect_score);

            Toast toast = new Toast(getApplicationContext()); //Use getApplicationContext here because we're in an activity
            toast.setGravity(Gravity.TOP, 0, 500);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(toastLinearLayout); //Use the toastLinearLayout view we declared a few lines above because we're in an activity
            toast.show();
        }

        //Section: Return to home page dialog
        goBackToSplashPageDialog = new Dialog(this);

        goToSplashPageButton = (ImageButton)findViewById(R.id.goToSplashPage);
        goToSplashPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGoBackToSplashPageDialog(view);
            }
        });
    }
    /* TALLY SCORES METHOD */
    private void tallyLogicScore(){
        overallScore = ++overallScore;
        logicalScore = ++logicalScore;
    }
    private void tallyMathScore(){
        overallScore = ++overallScore;
        mathScore = ++mathScore;
    }
    private void tallyVerbalScore(){
        overallScore = ++overallScore;
        verbalScore = ++verbalScore;
    }

    /* GO BACK TO SPLASH PAGE METHOD */
    public void showGoBackToSplashPageDialog(View view) {
        TextView yesButtonWithinGoBackDialog; //this is a local variable for the yes button
        TextView cancelButtonWithinGoBackDialog; //this is a local variable for the cancel button

        goBackToSplashPageDialog.setContentView(R.layout.activity_go_to_splash_page_dialog); //this sets the dialog activity to the dialog variable
        TextView goBackTextView = (TextView) goBackToSplashPageDialog.findViewById(R.id.goBackToSplashPageTextView);
        goBackTextView.setText(R.string.goToSplashPageDialogTextFromResults);

        goBackToSplashPageDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent); //This will hide the background that's in the dialog layout xml file so the rounded cardview corners will show
        goBackToSplashPageDialog.show(); //this opens the confirmation dialog asking is the user wants to return to the splash page

        yesButtonWithinGoBackDialog = (TextView) goBackToSplashPageDialog.findViewById(R.id.goBackToSplashPage); //sets the local variable to the yes button
        yesButtonWithinGoBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent intent = new Intent(ResultsPage.this, SplashPage.class); //opens the splash page
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
}
