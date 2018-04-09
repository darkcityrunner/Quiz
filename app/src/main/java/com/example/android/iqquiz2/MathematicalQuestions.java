package com.example.android.iqquiz2;


import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class MathematicalQuestions extends Fragment {

    private static final String TAG = "MathematicalQuestions";

    public interface mathematicalQuestionsInterface{
        void sendMathematicalQuestions(String math1Input, String math2Input, String math3Input, String math4Input, String math5Input);
    }

    /* GLOBAL VARIABLE DECLARATIONS */

    public mathematicalQuestionsInterface mMathematicalQuestionsInterface;

    LinearLayout toastLinearLayout; //Declare the linearlayout view for the custom toast message

    private Button saveAnswersButton;
    RadioGroup mathQuestion1_Frag_RadioGroup;
    EditText mathQuestion2_FragmentInput;
    RadioGroup mathQuestion3_Frag_RadioGroup;
    RadioGroup mathQuestion4_Frag_RadioGroup;
    EditText mathQuestion5_FragmentInput;

    RadioButton question1RadioButton;
    RadioButton question3RadioButton;
    RadioButton question4RadioButton;

    LinearLayout mathQ1Background;
    LinearLayout mathQ2Background;
    LinearLayout mathQ3Background;
    LinearLayout mathQ4Background;
    LinearLayout mathQ5Background;

    String math1Input;
    String math2Input;
    String math3Input;
    String math4Input;
    String math5Input;

    boolean errorFlag;

    public MathematicalQuestions() {
        // Required empty public constructor
    }

    /* ON CREATE VIEW */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            //SECTION: Set findviewbyid
            final View mathematicalView = inflater.inflate(R.layout.fragment_mathematical_questions, container, false);

            final View toastLayoutError = inflater.inflate(R.layout.toast_layout, container, false); //Sets the toast layout view variable to the toast_layout activity layout
            final View toastLayoutGoodJob = inflater.inflate(R.layout.toast_layout_good_job, container, false); //Sets the toast layout view variable to the toast_layout activity layout

            mathQuestion1_Frag_RadioGroup = (RadioGroup) mathematicalView.findViewById(R.id.mathQuestion1_Frag_RadioGroup);
            mathQuestion3_Frag_RadioGroup = (RadioGroup) mathematicalView.findViewById(R.id.mathQuestion3_Frag_RadioGroup);
            mathQuestion4_Frag_RadioGroup = (RadioGroup) mathematicalView.findViewById(R.id.mathQuestion4_Frag_RadioGroup);

            mathQ1Background = (LinearLayout) mathematicalView.findViewById(R.id.mathQuestion1_LinearLayout);
            mathQ2Background = (LinearLayout) mathematicalView.findViewById(R.id.mathQuestion2_LinearLayout);
            mathQ3Background = (LinearLayout) mathematicalView.findViewById(R.id.mathQuestion3_LinearLayout);
            mathQ4Background = (LinearLayout) mathematicalView.findViewById(R.id.mathQuestion4_LinearLayout);
            mathQ5Background = (LinearLayout) mathematicalView.findViewById(R.id.mathQuestion5_LinearLayout);

            // SECTION: SAVE USER ANSWERS BUTTON (ON CLICK LISTENER)
            saveAnswersButton = (Button) mathematicalView.findViewById(R.id.saveButton);
            saveAnswersButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {

                    // Section: Data Validation to determine if the user submitted an answer for each question
                    errorFlag = false;

                    // Question 1
                    if (mathQuestion1_Frag_RadioGroup.getCheckedRadioButtonId() <= 0) {
                        mathQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
                        errorFlag = true;
                    }
                    if (mathQuestion1_Frag_RadioGroup.getCheckedRadioButtonId() > 0) {
                        mathQ1Background.setBackgroundResource(0);
                    }
                    // Question 2
                    mathQuestion2_FragmentInput = (EditText) mathematicalView.findViewById(R.id.mathQuestion2_FragmentInput);
                    math2Input = mathQuestion2_FragmentInput.getText().toString();
                    if (TextUtils.isEmpty(math2Input)) {
                        mathQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
                        errorFlag = true;
                    } else {
                        mathQ2Background.setBackgroundResource(0);
                    }
                    // Question 3
                    if (mathQuestion3_Frag_RadioGroup.getCheckedRadioButtonId() <= 0) {
                        mathQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
                        errorFlag = true;
                    }
                    if (mathQuestion3_Frag_RadioGroup.getCheckedRadioButtonId() > 0) {
                        mathQ3Background.setBackgroundResource(0);
                    }
                    // Question 4
                    if (mathQuestion4_Frag_RadioGroup.getCheckedRadioButtonId() <= 0) {
                        mathQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
                        errorFlag = true;
                    }
                    if (mathQuestion4_Frag_RadioGroup.getCheckedRadioButtonId() > 0) {
                        mathQ4Background.setBackgroundResource(0);
                    }
                    // Question 5
                    mathQuestion5_FragmentInput = (EditText) mathematicalView.findViewById(R.id.mathQuestion5_FragmentInput);
                    math5Input = mathQuestion5_FragmentInput.getText().toString();
                    if (TextUtils.isEmpty(math5Input)) {
                        mathQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
                        errorFlag = true;
                    } else {
                        mathQ5Background.setBackgroundResource(0);
                    }

                    // Show Error Toast if the user left one or more questions blank
                    if (errorFlag == true) {
                        LayoutInflater inflater = getLayoutInflater();
                        toastLinearLayout = (LinearLayout) toastLayoutError.findViewById(R.id.toast_root); //This sets the linear layout for the toast message to the linearlayout view in the toast layout file

                        TextView toastText = toastLayoutError.findViewById(R.id.toast_message_TextView); //Have to include "toastLayout" before the findViewById because we're in a fragment
                        toastText.setText(R.string.answerEveryQuestionToastMessage);

                        Toast toast = new Toast(getActivity()); //Opens the toast layout, use getActivity because we're within a Fragment
                        toast.setGravity(Gravity.CENTER, 0, 0); //This will place the toast in the center of the screen
                        toast.setDuration(Toast.LENGTH_LONG);
                        toast.setView(toastLayoutError); //Use the view we declared at the top because we're within a Fragment
                        toast.show();
                        return;
                    }

                    // Section: Save User Input
                    // Question 1
                    int radioId1 = mathQuestion1_Frag_RadioGroup.getCheckedRadioButtonId();
                    question1RadioButton = mathematicalView.findViewById(radioId1);
                    math1Input = (String) question1RadioButton.getText();

                    // Question 2
                    // Question 2 user input is captured in the data validation because it is an EditText field

                    // Question 3
                    int radioId3 = mathQuestion3_Frag_RadioGroup.getCheckedRadioButtonId();
                    question3RadioButton = mathematicalView.findViewById(radioId3);
                    math3Input = (String) question3RadioButton.getText();

                    // Question 4
                    int radioId4 = mathQuestion4_Frag_RadioGroup.getCheckedRadioButtonId();
                    question4RadioButton = mathematicalView.findViewById(radioId4);
                    math4Input = (String) question4RadioButton.getText();

                    // Question 5
                    // Question 5 user input is captured in the data validation because it is an EditText field


                    //Toast to confirm the user answered the questions and should move to the next tab
                    LayoutInflater inflater = getLayoutInflater();
                    toastLinearLayout = (LinearLayout) toastLayoutGoodJob.findViewById(R.id.toast_root); //This sets the linear layout for the toast message to the linearlayout view in the toast layout file

                    TextView toastText = toastLayoutGoodJob.findViewById(R.id.toast_message_TextView); //Have to include "toastLayout" before the findViewById because we're in a fragment
                    toastText.setText(R.string.goodJobAnsweringAllQuestions);

                    Toast toast = new Toast(getActivity()); //Opens the toast layout, use getActivity because we're within a Fragment
                    toast.setGravity(Gravity.CENTER, 0, 0); //This will place the toast in the center of the screen
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(toastLayoutGoodJob); //Use the view we declared at the top because we're within a Fragment
                    toast.show(); //Show toast message

                    mMathematicalQuestionsInterface.sendMathematicalQuestions(math1Input, math2Input, math3Input, math4Input, math5Input);

                }
            });
            return mathematicalView;
        }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mMathematicalQuestionsInterface = (mathematicalQuestionsInterface) getActivity(); //Instantiates the listener
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}
