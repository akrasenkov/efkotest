package com.senik11.efkotest.database.notes;

import com.senik11.efkotest.domain.Note;
import com.senik11.efkotest.usecase.aware.CreateNote;
import com.senik11.efkotest.usecase.aware.GetNote;
import com.senik11.efkotest.usecase.aware.GetNotes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.inject.Inject;
import java.util.List;

public class NotesDatabaseProvider implements GetNote, GetNotes, CreateNote {

    private final SessionFactory sessionFactory;

    @Inject
    public NotesDatabaseProvider(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Note getNote(long id) throws NotFoundException {
        Note result = null;
        Session getSession = sessionFactory.openSession();
        getSession.beginTransaction();
        result = getSession.get(Note.class, id);
        getSession.getTransaction().commit();
        getSession.close();
        if (result == null) {
            throw new NotFoundException();
        }
        return result;
    }

    @Override
    public List<Note> getNotesByToken(String token) {
        List<Note> notes;
        Session getSession = sessionFactory.openSession();
        getSession.beginTransaction();
        notes = getSession.createQuery("FROM Note WHERE token = :token")
                .setString("token", token)
                .list();
        getSession.getTransaction().commit();
        getSession.close();
        return notes;
    }

    @Override
    public long createNote(Note note) {
        long id = -1;
        Session addSession = sessionFactory.openSession();
        addSession.beginTransaction();
        id = (long) addSession.save(note);
        addSession.getTransaction().commit();
        addSession.close();
        return id;
    }
}
