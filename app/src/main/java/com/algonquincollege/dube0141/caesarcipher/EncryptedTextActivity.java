package com.algonquincollege.dube0141.caesarcipher;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.CharacterPickerDialog;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import util.CaesarCipher;

import static com.algonquincollege.dube0141.caesarcipher.Constants.ABOUT_DIALOG_TAG;
import static com.algonquincollege.dube0141.caesarcipher.Constants.ROT13;
import static com.algonquincollege.dube0141.caesarcipher.Constants.ROTATIONS;
import static com.algonquincollege.dube0141.caesarcipher.Constants.THE_MESSAGE;
import static com.algonquincollege.dube0141.caesarcipher.Constants.THE_ROTATION;

/**
 *  Lab 7 Intents - Caesar Cipher
 *  @author Gabriel Dubé (dube0141)
 */

public class EncryptedTextActivity extends AppCompatActivity {
    private DialogFragment mAboutDialog;
    private EditText mEcryptedText;
    private CharacterPickerDialog mRotationsDialog;
    private int mRotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted_text);

        mEcryptedText = (EditText) findViewById(R.id.etEncrypted);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlainTextActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(THE_MESSAGE, mEcryptedText.getText().toString());
                intent.putExtra(THE_ROTATION, mRotation);
                startActivity(intent);
            }
        });

        mAboutDialog = new AboutDialogFragment();
        mRotation = ROT13;

        mRotationsDialog = new CharacterPickerDialog(this, new View(this), null, ROTATIONS, false) {
            @Override
            public void onClick(View v) {
                int index = ROTATIONS.indexOf(((Button) v).getText().toString());
                if (index >= 0) {
                    mRotation = index;
                }
                dismiss();
            }
        };

        Button clearButton = (Button) findViewById(R.id.bClearEncrypted);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEcryptedText.setText("");
            }
        });

        mEcryptedText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //NO-OP
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //NO-OP
            }

            @Override
            public void afterTextChanged(Editable s) {
                fab.setEnabled(s.length() != 0);
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String plainMessage = bundle.getString(THE_MESSAGE);
            final int rotation = bundle.getInt(THE_ROTATION);

            final String cipheredMsg = CaesarCipher.encrypt(plainMessage, rotation);

            mEcryptedText.setText(cipheredMsg);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            mAboutDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        if (id == R.id.action_about) {
            mAboutDialog.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        if (id == R.id.action_rotate) {
            mRotationsDialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
