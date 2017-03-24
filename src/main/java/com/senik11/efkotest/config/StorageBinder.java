package com.senik11.efkotest.config;

import com.senik11.efkotest.database.notes.NotesDatabaseProvider;
import com.senik11.efkotest.usecase.aware.CreateNote;
import com.senik11.efkotest.usecase.aware.GetNote;
import com.senik11.efkotest.usecase.aware.GetNotes;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

public class StorageBinder extends AbstractBinder {
    @Override
    protected void configure() {
        bind(NotesDatabaseProvider.class).to(GetNote.class).in(RequestScoped.class);
        bind(NotesDatabaseProvider.class).to(GetNotes.class).in(RequestScoped.class);
        bind(NotesDatabaseProvider.class).to(CreateNote.class).in(RequestScoped.class);
    }
}
