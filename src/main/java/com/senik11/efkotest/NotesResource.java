package com.senik11.efkotest;

import com.senik11.efkotest.auth.annotation.AuthData;
import com.senik11.efkotest.auth.Bracelet;
import com.senik11.efkotest.domain.Note;
import com.senik11.efkotest.usecase.notes.CreateNoteUseCase;
import com.senik11.efkotest.usecase.notes.GetNoteUseCase;
import com.senik11.efkotest.usecase.notes.GetNotesUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Date;

@Path(NotesResource.PATH)
public class NotesResource {
    private final Logger log = LoggerFactory.getLogger(NotesResource.class);

    public static final String PATH = "/notes";

    private static final String CATEGORY_PARAM = "category";
    private static final String TEXT_PARAM = "category";
    private static final String NOTE_ID_PARAM = "noteId";

    @Inject
    private GetNoteUseCase getNote;

    @Inject
    private GetNotesUseCase getNotes;

    @Inject
    private CreateNoteUseCase createNote;

    @POST
    public Response createNote(
            @AuthData Bracelet bracelet,
            @QueryParam(CATEGORY_PARAM) String category,
            @QueryParam(TEXT_PARAM) String text
    ) {
        log.info("createNote > bracelet = {}, category = {}, text = {}", bracelet, category, text);

        if (category == null || category.isEmpty()) {
            throw new BadRequestException("Category can not be empty!");
        }
        if (text == null || text.isEmpty()) {
            throw new BadRequestException("Text can not be empty!");
        }

        Note note = new Note(category, text, bracelet.getToken(), new Date());
        long noteId = createNote.createNote(note);
        URI noteUri = UriBuilder.fromResource(NotesResource.class).path(String.valueOf(noteId)).build();

        return Response.created(noteUri).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Iterable<Note> getNotes(
            @AuthData Bracelet bracelet
    ) {
        log.debug("getNotes > bracelet = {}");

        return getNotes.getNotesByToken(bracelet.getToken());
    }

    @GET
    @Path("/{noteId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Note getNote(
            @AuthData Bracelet bracelet,
            @PathParam(NOTE_ID_PARAM) long noteId
    ) {
        log.debug("getNote > bracelet = {}, noteId = {}", bracelet, noteId);

        try {
            return getNote.getNoteById(noteId);
        } catch (GetNoteUseCase.NotFoundException e) {
            throw new NotFoundException("Note not found");
        }
    }
}
