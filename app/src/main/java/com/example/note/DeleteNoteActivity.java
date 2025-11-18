package com.example.note;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class DeleteNoteActivity extends AppCompatActivity {

    private Spinner spinnerNotes;
    private NoteManager noteManager;
    private List<Notess> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_note);

        spinnerNotes = findViewById(R.id.spinner_notes);
        Button buttonDelete = findViewById(R.id.button_delete);
        noteManager = new NoteManager(this);

        loadNotesToSpinner();

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteSelectedNote();
            }
        });
    }

    private void loadNotesToSpinner() {
        noteList = noteManager.getNotes();

        if (noteList.isEmpty()) {
            Toast.makeText(this, "No notes available to delete.", Toast.LENGTH_SHORT).show();
            spinnerNotes.setEnabled(false);
            return;
        }

        // ArrayAdapter naudoja Note.toString() (užrašo pavadinimą)
        ArrayAdapter<Notess> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                noteList
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerNotes.setAdapter(adapter);
    }

    private void deleteSelectedNote() {
        if (noteList.isEmpty() || spinnerNotes.getSelectedItem() == null) {
            Toast.makeText(this, "No note selected.", Toast.LENGTH_SHORT).show();
            return;
        }

        Notess selectedNote = (Notess) spinnerNotes.getSelectedItem();
        noteManager.deleteNote(selectedNote);

        Toast.makeText(this, "Note '" + selectedNote.getName() + "' deleted.", Toast.LENGTH_SHORT).show();
        finish();
    }
}