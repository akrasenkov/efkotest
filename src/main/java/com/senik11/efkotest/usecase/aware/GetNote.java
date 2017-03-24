package com.senik11.efkotest.usecase.aware;

import com.senik11.efkotest.domain.Note;

public interface GetNote {

    Note getNote(long id) throws NotFoundException;

    class NotFoundException extends Exception {}
}
