package com.senik11.efkotest.usecase.aware;

import com.senik11.efkotest.domain.Note;

import java.util.List;

public interface GetNotes {

    List<Note> getNotesByToken(String token);

}
