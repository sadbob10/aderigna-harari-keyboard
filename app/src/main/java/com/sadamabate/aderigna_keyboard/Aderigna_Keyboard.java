package com.sadamabate.aderigna_keyboard;

import android.inputmethodservice.InputMethodService;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.media.AudioManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputConnection;

public class Aderigna_Keyboard extends InputMethodService implements KeyboardView.OnKeyboardActionListener {

    private KeyboardView keyboardView;
    private Keyboard keyboard;
    int key_family = 0;
    int first_time_selected = 0;

    @Override
    public View onCreateInputView() {
        keyboardView = (KeyboardView) getLayoutInflater().inflate(R.layout.keyboard, null);
        keyboard = new Keyboard(this, R.xml.first_key);
        keyboardView.setKeyboard(keyboard);
        keyboardView.setOnKeyboardActionListener(this);
        return keyboardView;
    }

    @Override
    public void onPress(int i) {

    }

    @Override
    public void onRelease(int i) {

    }

    @Override
    public void onKey(int primaryCode, int[] keyCodes) {
        InputConnection inputConnection = getCurrentInputConnection();
        familyKeyKeyboard(inputConnection,primaryCode);


        switch (primaryCode)
        {
            case Keyboard.KEYCODE_DELETE:
                inputConnection.deleteSurroundingText(1,0);
                break;
            case Keyboard.KEYCODE_DONE:
                inputConnection.sendKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN,KeyEvent.KEYCODE_ENTER));
                break;
            default:
                char c = (char)primaryCode;
                inputConnection.commitText(String.valueOf(c),1);


        }


    }

    private void   playClick(int i){
        AudioManager audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        switch (i){
            default:
                audioManager.playSoundEffect(audioManager.FX_KEYPRESS_STANDARD);
        }
    }

    private void familyKeyKeyboard(InputConnection inputConnection, int i )
    {
        if((i!=-2) && (i !=-1))
        {
            if((key_family !=0)&&(key_family !=i)&&(i>=key_family -5 && i>=key_family+2))
            {
                if (first_time_selected==0)
                {
                    inputConnection.deleteSurroundingText(1,0);
                }
                first_time_selected ++;
                return;
            }
            if (i==32||i==4963||i==4962||i==-4||i==-5)
            {
                return;
            }
            if ((i>=35&&i<=65)||(i>=4969&&i<=4988)||i==4667||i==123||i==125)
            {

            }
        }
        switch(i)
        {
            case 4608:
            case 4609:
            case 4610:
            case 4611:
            case 4612:
            case 4613:
            case 4614:
            case 4615:
                key_family =4608;
                first_time_selected = 0;
                keyboard = new Keyboard(this,R.xml.h_family);
                keyboardView.setKeyboard(keyboard);
                keyboardView.setOnKeyboardActionListener(this);
                break;
            case 4616:
            case 4617:
            case 4618:
            case 4619:
            case 4620:
            case 4621:
            case 4622:
            case 4623:
                key_family =4616;
                first_time_selected = 0;
                keyboard = new Keyboard(this,R.xml.l_family);
                keyboardView.setKeyboard(keyboard);
                keyboardView.setOnKeyboardActionListener(this);
                break;
        }

    }

    @Override
    public void onText(CharSequence charSequence) {

    }

    @Override
    public void swipeLeft() {

    }

    @Override
    public void swipeRight() {

    }

    @Override
    public void swipeDown() {

    }

    @Override
    public void swipeUp() {

    }
}
