package com.example.android.iqquiz2;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class VerbalQuestions extends Fragment {

    private static final String TAG = "VerbalQuestions";

    public interface verbalQuestionsInterface{
        void sendVerbalQuestions(String verbal1Input, String verbal2Input, String verbal3Input, String verbal4Input1, String verbal4Input2, String verbal4Input3, String verbal4Input4, String verbal5Input);
    }

    /* GLOBAL VARIABLE DECLARATIONS */

    public verbalQuestionsInterface mVerbalQuestionsInterface;

    LinearLayout toastLinearLayout; //Declare the linearlayout view for the custom toast message

    private Button saveAnswersButton;
    TextView verbalQuestion1_FragmentInput;
    TextView verbalQuestion2_FragmentInput;
    RadioGroup verbalQuestion3_Frag_RadioGroup;
    CheckBox verbalQuestion4_CheckBox1;
    CheckBox verbalQuestion4_CheckBox2;
    CheckBox verbalQuestion4_CheckBox3;
    CheckBox verbalQuestion4_CheckBox4;
    RadioGroup verbalQuestion5_Frag_RadioGroup;

    RadioButton question3RadioButton;
    RadioButton question5RadioButton;

    LinearLayout verbalQ1Background;
    LinearLayout verbalQ2Background;
    LinearLayout verbalQ3Background;
    LinearLayout verbalQ4Background;
    LinearLayout verbalQ5Background;

    String verbal4Input1;
    String verbal4Input2;
    String verbal4Input3;
    String verbal4Input4;

    boolean errorFlag;
    boolean Q4errorFlag;

    public VerbalQuestions() {
        // Required empty public constructor
    }

    /* ON CREATE VIEW */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //SECTION: Set findviewbyid
        final View verbalView = inflater.inflate(R.layout.fragment_verbal_questions, container, false);

        final View toastLayoutError = inflater.inflate(R.layout.toast_layout, container, false); //Sets the toast layout view variable to the toast_layout activity layout
        final View toastLayoutGoodJob = inflater.inflate(R.layout.toast_layout_good_job, container, false); //Sets the toast layout view variable to the toast_layout activity layout

        verbalQuestion3_Frag_RadioGroup = (RadioGroup) verbalView.findViewById(R.id.verbalQuestion3_Frag_RadioGroup);
        verbalQuestion5_Frag_RadioGroup = (RadioGroup) verbalView.findViewById(R.id.verbalQuestion5_Frag_RadioGroup);

        verbalQuestion4_CheckBox1 = (CheckBox) verbalView.findViewById(R.id.verbalQuestion4_CheckBox1);
        verbalQuestion4_CheckBox2 = (CheckBox) verbalView.findViewById(R.id.verbalQuestion4_CheckBox2);
        verbalQuestion4_CheckBox3 = (CheckBox) verbalView.findViewById(R.id.verbalQuestion4_CheckBox3);
        verbalQuestion4_CheckBox4 = (CheckBox) verbalView.findViewById(R.id.verbalQuestion4_CheckBox4);

        verbalQ1Background = (LinearLayout) verbalView.findViewById(R.id.verbalQuestion1_LinearLayout);
        verbalQ2Background = (LinearLayout) verbalView.findViewById(R.id.verbalQuestion2_LinearLayout);
        verbalQ3Background = (LinearLayout) verbalView.findViewById(R.id.verbalQuestion3_LinearLayout);
        verbalQ4Background = (LinearLayout) verbalView.findViewById(R.id.verbalQuestion4_LinearLayout);
        verbalQ5Background = (LinearLayout) verbalView.findViewById(R.id.verbalQuestion5_LinearLayout);

        // SECTION: SAVE USER ANSWERS BUTTON (ON CLICK LISTENER)
        saveAnswersButton = (Button) verbalView.findViewById(R.id.saveButton);
        saveAnswersButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                // Section: Data Validation to determine if the user submitted an answer for each question
                errorFlag = false;
                Q4errorFlag = true;
                verbal4Input1 = "";
                verbal4Input2 = "";
                verbal4Input3 = "";
                verbal4Input4 = "";

                // Question 1
                verbalQuestion1_FragmentInput = (EditText) verbalView.findViewById(R.id.verbalQuestion1_FragmentInput);
                String verbal1Input = (String) verbalQuestion1_FragmentInput.getText().toString();
                if (TextUtils.isEmpty(verbal1Input)){
                    verbalQ1Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                else {
                    verbalQ1Background.setBackgroundResource(0);
                }
                // Question 2
                verbalQuestion2_FragmentInput = (EditText) verbalView.findViewById(R.id.verbalQuestion2_FragmentInput);
                String verbal2Input = (String) verbalQuestion2_FragmentInput.getText().toString();
                if (TextUtils.isEmpty(verbal2Input)) {
                    verbalQ2Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag = true;
                }
                else {
                    verbalQ2Background.setBackgroundResource(0);
                }
                // Question 3
                if (verbalQuestion3_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    verbalQ3Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (verbalQuestion3_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    verbalQ3Background.setBackgroundResource(0);
                }

                // Question 4
                boolean q4b1 = verbalQuestion4_CheckBox1.isChecked(); //Check if the checkbox is checked and return true to the variable if checked
                if (q4b1) {
                    verbal4Input1 = (String) verbalQuestion4_CheckBox1.getText();
                    verbalQ4Background.setBackgroundResource(0);
                    Q4errorFlag = false;
                }
                boolean q4b2 = verbalQuestion4_CheckBox2.isChecked(); //Check if the checkbox is checked and return true to the variable if checked
                if (q4b2) {
                    verbal4Input2 = (String) verbalQuestion4_CheckBox2.getText();
                    verbalQ4Background.setBackgroundResource(0);
                    Q4errorFlag = false;
                }
                boolean q4b3 = verbalQuestion4_CheckBox3.isChecked(); //Check if the checkbox is checked and return true to the variable if checked
                if (q4b3) {
                    verbal4Input3 = (String) verbalQuestion4_CheckBox3.getText();
                    verbalQ4Background.setBackgroundResource(0);
                    Q4errorFlag = false;
                }
                boolean q4b4 = verbalQuestion4_CheckBox4.isChecked(); //Check if the checkbox is checked and return true to the variable if checked
                if (q4b4) {
                    verbal4Input4 = (String) verbalQuestion4_CheckBox4.getText();
                    verbalQ4Background.setBackgroundResource(0);
                    Q4errorFlag = false;
                }
                if (Q4errorFlag){
                    verbalQ4Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }

                // Question 5
                if (verbalQuestion5_Frag_RadioGroup.getCheckedRadioButtonId()<=0){
                    verbalQ5Background.setBackgroundResource(R.drawable.cardview_border_red);
                    errorFlag=true;
                }
                if (verbalQuestion5_Frag_RadioGroup.getCheckedRadioButtonId()>0){
                    verbalQ5Background.setBackgroundResource(0);
                }

                // Show Error Toast if the user left one or more questions blank
                if (errorFlag){
                    LayoutInflater inflater = getLayoutInflater();
                    toastLinearLayout = (LinearLayout) toastLayoutError.findViewById(R.id.toast_root); //This sets the linear layout for the toast message to the linearlayout view in the toast layout file

                    TextView toastText = toastLayoutError.findViewById(R.id.toast_message_TextView); //Have to include "toastLayout" before the findViewById because we're in a fragment
                    toastText.setText(R.string.answerEveryQuestionToastMessage);

                    Toast toast = new Toast(getActivity()); //Opens the toast layout, use getActivity because we're within a Fragment
                    toast.setGravity(Gravity.CENTER,0,0); //This will place the toast in the center of the screen
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.setView(toastLayoutError); //Use the view we declared at the top because we're within a Fragment
                    toast.show();
                    return;
                }

                // Section: Save User Input
                // Question 1 user input is captured in the data validation

                // Question 2
                // Question 2 user input is captured in the data validation

                // Question 3
                int radioId3 = verbalQuestion3_Frag_RadioGroup.getCheckedRadioButtonId();
                question3RadioButton = verbalView.findViewById(radioId3);
                String verbal3Input = (String) question3RadioButton.getText();

                // Question 4
                // Question 4 user input is captured in the data validation

                // Question 5
                int radioId5 = verbalQuestion5_Frag_RadioGroup.getCheckedRadioButtonId();
                question5RadioButton = verbalView.findViewById(radioId5);
                String verbal5Input = (String) question5RadioButton.getText();

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

                mVerbalQuestionsInterface.sendVerbalQuestions(verbal1Input, verbal2Input, verbal3Input, verbal4Input1, verbal4Input2, verbal4Input3, verbal4Input4, verbal5Input);

            }
        });
        return verbalView;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            mVerbalQuestionsInterface = (verbalQuestionsInterface) getActivity(); //Instantiates the listener
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }
}