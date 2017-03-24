package com.senik11.efkotest.usecase.notes;

import com.senik11.efkotest.domain.Note;
import com.senik11.efkotest.usecase.aware.CreateNote;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;

public class CreateNoteUseCase {

    private static final long INVALID_ID = -1L;

    private CreateNote createNote;

    @Inject
    public CreateNoteUseCase(CreateNote createNote) {
        this.createNote = createNote;
    }

    public long createNote(Note note) {
        long id = createNote.createNote(note);
        if (id == INVALID_ID) {
            throw new InternalServerErrorException("Error creating note.");
        }
        return id;
    }
}
