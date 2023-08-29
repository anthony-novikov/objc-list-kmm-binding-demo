package com.example.objclistbinding

object Demo {
    fun getStringBox(): GenericBox<String> {
        return GenericBox("Single String")
    }

    fun getListOfStringsBox1(): GenericBox<List<String>> {
        return GenericBox(listOf("List", "of", "strings"))
    }

    fun getListOfStringsBox2(): GenericListBox<String> {
        return GenericListBox(listOf("List", "of", "strings"))
    }

    fun getBoxOfStringBoxes(): GenericBox<ChildGenericBox<String>> {
        return GenericBox(ChildGenericBox("Boxed String"))
    }
}
