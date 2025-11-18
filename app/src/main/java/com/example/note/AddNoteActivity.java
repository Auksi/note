package com.example.note;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextContent;
    private NoteManager noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextName = findViewById(R.id.edit_text_name);
        editTextContent = findViewById(R.id.edit_text_content);
        Button buttonSave = findViewById(R.id.button_save);
        noteManager = new NoteManager(this);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });
    }

    private void saveNote() {
        String name = editTextName.getText().toString().trim();
        String content = editTextContent.getText().toString().trim();

        // Teksto tikrinimas (Text Validation)
        if (name.isEmpty() || content.isEmpty()) {
            Toast.makeText(this, "Note name and content cannot be empty!", Toast.LENGTH_SHORT).show();
            return;
        }

        Notess newNote = new Notess(name, content);
        noteManager.addNote(newNote);

        Toast.makeText(this, "Note saved successfully.", Toast.LENGTH_SHORT).show();
        finish();
    }
}