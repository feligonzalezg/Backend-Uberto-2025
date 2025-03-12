package uberto.backendgrupo72025.Repository

abstract class Repository<T : ItemRepo> {
    abstract val items: MutableSet<T>

    abstract var nameEntityRepo: String

    private fun itemExists(item: T): Boolean = items.map { it.id }.contains(item.id)

    fun createItem(item: T): Int {
        if (itemExists(item)) {
            throw Exception("El item ya existe en el repositorio.")
        }

        val lastId = items.maxOfOrNull { it.id }
        val newId = if (lastId != null) lastId + 1 else 1
        item.id = newId
        items.add(item)
        return item.id
    }

    fun updateItem(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }

        items.removeIf { it.id == item.id }
        items.add(item)
    }

    fun deleteItem(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }
        items.removeIf { it.id == item.id }
    }

    fun itemById(id: Int, entityType: String = ""): T {

        val item = items.find { it.id == id }
        if (item == null && entityType == "") {
            throw RuntimeException("El id buscado no existe")
        }
        if (item == null) {
            throw RuntimeException(entityType)
        }
        return item
    }

    abstract fun searchItems(patron: String): List<T>

    fun items() = items.toList()

    fun cleanForTests() = items.clear()

    fun totalItems(): Int = items.size

}

interface ItemRepo {
    var id: Int
}
