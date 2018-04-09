package com.example.android.iqquiz2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

import junit.framework.Test;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogicalQuestions extends Fragment {

    private static final String TAG = "LogicalQuestions";

    public interface logicalQuestionsInterface{
        void sendLogicalQuestions(String logic1Input, String logic2Input, String logic3Input, String logic4Input, String logic5Input);
    }

    /* GLOBAL VARIABLE DECLARATIONS */
    public logicalQuestionsInterface mLogicalQuestionsInterface;

    LinearLayout toastLinearLayout; //Declare the linearlayout view for the custom toast message

    private Button saveAnswersButton;
    TextView logicQuestion1_FragmentInput;
    RadioGroup logicQuestion2_Frag_RadioGroup;
    RadioGroup logicQuestion3_Frag_RadioGroup;
    RadioGroup logicQuestion4_Frag_RadioGroup;
    RadioGroup logicQuestion5_Frag_RadioGroup;

    RadioButton question2RadioButton;
    RadioButton question3RadioButton;
    RadioButton question4RadioButton;
    RadioButton question5RadioButton;

    LinearLayout logicalQ1Background;
    LinearLayout logicalQ2Background;
    LinearLayout logicalQ3Background;
    LinearLayout logicalQ4Background;
    LinearLayout logicalQ5Background;

    boolean errorFlag;

    public LogicalQuestions() {
        // Required empty public constructor
    }

    /* ON CREATE VIEW */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //SECTION: Set findviewbyid
        final View logicalView = inflater.inflate(R.layout.fragment_logical_questions, container, false);

        final View toastLayoutError = inflater.inflate(R.layout.toast_layout, container, false); //Sets the toast layout view variable to the toast_layout activity layout
        final View toastLayoutGoodJob = inflater.inflate(R.layout.toast_layout_good_job, container, false); //Sets the toast layout view variable to the toast_layout activity layout

        logicQuestion2_Frag_RadioGroup = (RadioGroup) logicalView.findViewById(R.id.logicQuestion2_Frag_RadioGroup);
        logicQuestion3_Frag_RadioGroup = (RadioGroup) logicalView.findViewById(R.id.logicQuestion3_Frag_RadioGroup);
        logicQuestion4_Frag_RadioGroup = (RadioGroup) logicalView.findViewById(R.id.logicQuestion4_Frag_RadioGroup);
        logicQuestion5_Frag_RadioGroup = (RadioGroup) logicalView.findViewById(R.id.logicQuestion5_Frag_RadioGroup);

        logicalQ1Background = (LinearLayout) logicalView.findViewById(R.id.logicQuestion1_LinearLayout);
        logicalQ2Background = (LinearLayout) logicalView.findViewById(R.id.logicQuestion2_LinearLayout);
        logicalQ3Background = (LinearLayout) logicalView.findViewById(R.id.logicQuestion3_LinearLayout);
        logicalQ4Background = (LinearLayout) logicalView.findViewById(R.id.logicQuestion4_LinearLayout);
        logicalQ5Background = (LinearLayout) logicalView.findViewById(R.id.logicQuestion5_LinearLayout);

        // SECTION: SAVE USER ANSWERS BUTTON (ON CLICK LISTENER)
        saveAnswersButton = (Button) logicalView.findViewById(R.id.saveButton);
        saveAnswersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Section: Data Validation to determine if the user submitted an answer for each question
                errorFlag = false;
                // Question 1
                logicQuestion1_FragmentInput = (EditText) logicalView.findViewById(R.id.logicQuestion1_FragmentInput);
                String logic1Input = (String) logicQuestion1_FragmentInput.getText().toString();
                if (TextUtils.isEmpty(logic1Input)){
                    logicalQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                else {
                    logicalQ1Background.setBackgroundResource(0);
                }
                // Question 2
                if (logicQuestion2_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    logicalQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (logicQuestion2_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    logicalQ2Background.setBackgroundResource(0);
                }
                // Question 3
                if (logicQuestion3_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    logicalQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (logicQuestion3_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    logicalQ3Background.setBackgroundResource(0);
                }
                // Question 4
                if (logicQuestion4_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    logicalQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (logicQuestion4_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    logicalQ4Background.setBackgroundResource(0);
                }
                // Question 5
                if (logicQuestion5_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    logicalQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (logicQuestion5_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    logicalQ5Background.setBackgroundResource(0);
                }

                // Show Error Toast if the user left one or more questions blank
                if (errorFlag == true){
                    LayoutInflater inflater = getLayoutInflater();
                    toastLinearLayout = (LinearLayout) toastLayoutError.findViewById(R.id.toast_root); //This sets the linear layout for the toast message to the linearlayout view in the toast layout file

                    TextView toastText = toastLayoutError.findViewById(R.id.toast_message_TextView); //Have to include "toastLayout" before the findViewById because we're in a fragment
                    toastText.setText(R.string.answerEveryQuestionToastMessage);

                    Toast toast = new Toast(getActivity()); //Opens the toast layout, use getActivity because we're within a Fragment
                    toast.setGravity(Gravity.CENTER,0,0); //This will place the toast in the center of the screen
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setView(toastLayoutError); //Use the view we declared at the top because we're within a Fragment
                    toast.show();
                    return;
                }

                // Section: Save User Input
            // Question 1 user input is captured in the data validation because it is an EditText field

            // Question 2
                int radioId2 = logicQuestion2_Frag_RadioGroup.getCheckedRadioButtonId();
                question2RadioButton = logicalView.findViewById(radioId2);
                String logic2Input = (String) question2RadioButton.getText();

            // Question 3
                int radioId3 = logicQuestion3_Frag_RadioGroup.getCheckedRadioButtonId();
                question3RadioButton = logicalView.findViewById(radioId3);
                String logic3Input = (String) question3RadioButton.getText();

            // Question 4
                int radioId4 = logicQuestion4_Frag_RadioGroup.getCheckedRadioButtonId();
                question4RadioButton = logicalView.findViewById(radioId4);
                String logic4Input = (String) question4RadioButton.getText();

            // Question 5
                int radioId5 = logicQuestion5_Frag_RadioGroup.getCheckedRadioButtonId();
                question5RadioButton = logicalView.findViewById(radioId5);
                String logic5Input = (String) question5RadioButton.getText();

                //Toast to confirm the user answered the questions and should move to the next tab

                LayoutInflater inflater = getLayoutInflater();
                toastLinearLayout = (LinearLayout) toastLayoutGoodJob.findViewById(R.id.toast_root); //This sets the linear layout for the toast message to the linearlayout view in the toast layout file

                TextView toastText = toastLayoutGoodJob.findViewById(R.id.toast_message_TextView); //Have to include "toastLayout" before the findViewById because we're in a fragment
                toastText.setText(R.string.goodJobAnsweringAllQuestions);
                Toast toast = new Toast(getActivity()); //Opens the toast layout, use getActivity because we're within a Fragment
                toast.setGravity(Gravity.CENTER, 0, 0); //This will place the toast in the center of the screen
                toast.setDuration(Toast.LENGTH_LONG);
                toast.setView(toastLayoutGoodJob); //Use the view we declared at the top because we're within a Fragment
                toast.show(); //Show toast message

                mLogicalQuestionsInterface.sendLogicalQuestions(logic1Input, logic2Input, logic3Input, logic4Input, logic5Input);

            }
            });
        return logicalView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mLogicalQuestionsInterface = (logicalQuestionsInterface) getActivity(); //Instantiates the listener
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}




