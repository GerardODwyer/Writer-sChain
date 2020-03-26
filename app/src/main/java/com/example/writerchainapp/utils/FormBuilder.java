package com.example.writerchainapp.utils;

import com.dariopellegrini.formbuilder.FormElement;
import com.dariopellegrini.formbuilder.FormHeader;
import com.dariopellegrini.formbuilder.FormObject;

import java.util.ArrayList;
import java.util.List;

public class FormBuilder {
    //FormBuilder formBuilder = new FormBuilder(this, mLinearLayout);
    public static List<FormObject> formObjects = new ArrayList<FormObject>();

    // Header
    public static void getFormHeader(String title) {
        formObjects.add(new FormHeader().setTitle(title));
    }


    public static void getFormTextType(String tag, String hint){
        formObjects.add(new FormElement().setTag(tag).setHint(hint).setType(FormElement.Type.TEXT));
    }

    public static void getFormNumberType(String tag, String hint){
        formObjects.add(new FormElement().setTag(tag).setHint(hint).setType(FormElement.Type.NUMBER));
    }

//    public static void getFormTextType(String tag, String hint, Enum formType){
//        formObjects.add(new FormElement().setTag(tag).setHint(hint).setType(FormElement.Type.TEXT));
//    }
//
//    public static void getFormTextType(String tag, String hint, Enum formType){
//        formObjects.add(new FormElement().setTag(tag).setHint(hint).setType(FormElement.Type.TEXT));
//    }
//
}
