package com.example.note;

import android.os.Bundle;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView listViewNotes;
    private TextView textViewEmpty;
    private NoteManager noteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewNotes = findViewById(R.id.list_view_notes);
        textViewEmpty = findViewById(R.id.text_view_empty);
        noteManager = new NoteManager(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Reload list every time the activity comes to the foreground
        loadNotesList();
    }

    private void loadNotesList() {
        List<Notess> notes = noteManager.getNotes();

        if (notes.isEmpty()) {
            textViewEmpty.setVisibility(View.VISIBLE);
            listViewNotes.setVisibility(View.GONE);
        } else {
            textViewEmpty.setVisibility(View.GONE);
            listViewNotes.setVisibility(View.VISIBLE);

            ArrayAdapter<Notess> adapter = new ArrayAdapter<>(
                    this,
                    android.R.layout.simple_list_item_1,
                    notes
            );
            listViewNotes.setAdapter(adapter);
        }
    }

    // Meniu sukurimas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    // Meniu pasirinkimo apdorojimas
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_note) {
            // Call AddNoteActivity
            startActivity(new Intent(this, AddNoteActivity.class));
            return true;
        } else if (id == R.id.action_delete_note) {
            // Call DeleteNoteActivity
            startActivity(new Intent(this, DeleteNoteActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}