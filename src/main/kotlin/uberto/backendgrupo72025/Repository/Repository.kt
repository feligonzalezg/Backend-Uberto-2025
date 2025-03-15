package uberto.backendgrupo72025.Repository

abstract class Repository<T : ItemRepo> {
    abstract val items: MutableSet<T>

    abstract var nameEntityRepo: String

    private fun itemExists(item: T): Boolean = items.map { it.id }.contains(item.id)

    fun save(item: T): Long {
        if (itemExists(item)) {
            update(item)
        }

        val lastId = items.maxOfOrNull { it.id }
        val newId = if (lastId != null) lastId + 1 else 1
        item.id = newId
        items.add(item)
        return item.id
    }

    fun update(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }

        items.removeIf { it.id == item.id }
        items.add(item)
    }

    fun delete(item: T) {
        if (!itemExists(item)) {
            throw Exception("No hay un item con ese ID en el repositorio.")
        }
        items.removeIf { it.id == item.id }
    }

    fun findById(id: Long, entityType: String = ""): T {

        val item = items.find { it.id == id }
        if (item == null && entityType == "") {
            throw RuntimeException("El id buscado no existe")
        }
        if (item == null) {
            throw RuntimeException(entityType)
        }
        return item
    }

    fun findAll() = items.toList()

    fun cleanForTests() = items.clear()

    fun totalItems(): Int = items.size

}

interface ItemRepo {
    var id: Long
}
