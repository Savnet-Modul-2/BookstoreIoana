package com.modul2.bookstore.dto.validation;

import jakarta.validation.GroupSequence;

//@GroupSequence
//Specifică ordinea în care vor fi aplicate grupurile de validare.
//Este folosită pentru a evita ca toate grupurile să fie validate simultan.
//Dacă o validare dintr-un grup anterior eșuează, grupurile următoare nu mai sunt validate deloc.
//Mai intai se executa BasicValidation, apoi AdvancedValidation
@GroupSequence({BasicValidation.class, AdvancedValidation.class})
public interface ValidationOrder {
}
