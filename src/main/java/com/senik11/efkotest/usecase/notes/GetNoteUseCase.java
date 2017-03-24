package com.senik11.efkotest.usecase.notes;

import com.senik11.efkotest.domain.Note;
import com.senik11.efkotest.usecase.aware.GetNote;

import javax.inject.Inject;

public class GetNoteUseCase {

    private GetNote getNote;

    @Inject
    public GetNoteUseCase(GetNote getNote) {
        this.getNote = getNote;
    }

    public Note getNoteById(long id) throws NotFoundException {
        try {
            return getNote.getNote(id);
        } catch (GetNote.NotFoundException nfe) {
            throw new NotFoundException("Note not found");
        }
    }

    public static class NotFoundException extends Exception {
        public NotFoundException(String message) {
            super(message);
        }
    }
}
