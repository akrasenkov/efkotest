package com.senik11.efkotest.config;

import com.senik11.efkotest.usecase.notes.CreateNoteUseCase;
import com.senik11.efkotest.usecase.notes.GetNoteUseCase;
import com.senik11.efkotest.usecase.notes.GetNotesUseCase;
import org.glassfish.hk2.utilities.binding.AbstractBinder;

public class UseCaseBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(GetNoteUseCase.class).to(GetNoteUseCase.class);
        bind(CreateNoteUseCase.class).to(CreateNoteUseCase.class);
        bind(GetNotesUseCase.class).to(GetNotesUseCase.class);
    }
}
