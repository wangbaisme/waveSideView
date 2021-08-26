package com.gdszzy.test03.wavesidebar

import com.gdszzy.test03.wavesidebar.ui.ContactModel
import com.gdszzy.test03.wavesidebar.ui.ContactModel.ContactGroup
import java.util.*

fun main () {
    var mContactModels: List<ContactModel>? = null
    var mShowModels: List<ContactGroup>? = null
    mContactModels = ArrayList<ContactModel>()
    mShowModels = ArrayList<ContactGroup>()
    mContactModels.addAll(ContactModel.getContacts())
    mShowModels.addAll(ContactModel.getContactGroup(mContactModels))
    mShowModels
}