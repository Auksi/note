package com.example.note;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class NoteManager {
    private static final String PREFS_NAME = "NotePrefs";
    private static final String NOTES_KEY = "notesList";
    private SharedPreferences sharedPreferences;
    private Gson gson = new Gson();

    public NoteManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public List<Notess> getNotes() {
        String json = sharedPreferences.getString(NOTES_KEY, null);
        if (json == null) {
            return new ArrayList<>();
        }
        Type type = new TypeToken<ArrayList<Notess>>() {}.getType();
        return gson.fromJson(json, type);
    }

    private void saveNotes(List<Notess> notes) {
        String json = gson.toJson(notes);
        sharedPreferences.edit().putString(NOTES_KEY, json).apply();
    }

    public void addNote(Notess note) {
        List<Notess> notes = getNotes();
        notes.add(note);
        saveNotes(notes);
    }

    public void deleteNote(Notess noteToDelete) {
        List<Notess> notes = getNotes();
        // Remove the note by matching its name
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getName().equals(noteToDelete.getName())) {
                notes.remove(i);
                break;
            }
        }
        saveNotes(notes);
    }
}
