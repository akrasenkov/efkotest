package com.senik11.efkotest.usecase.notes;

import com.senik11.efkotest.domain.Note;
import com.senik11.efkotest.usecase.aware.GetNotes;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.util.Collection;

public class GetNotesUseCase {

    private final GetNotes getNotes;

    @Inject
    public GetNotesUseCase(GetNotes getNotes) {
        this.getNotes = getNotes;
    }

    public Collection<Note> getNotesByToken(String token) {
        Collection<Note> notes = getNotes.getNotesByToken(token);
        if (notes == null) {
            throw new InternalServerErrorException("Failed to get notes");
        }
        return notes;
    }
}
