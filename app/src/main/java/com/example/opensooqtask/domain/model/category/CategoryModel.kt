import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class CategoryModel() : RealmObject {
    @PrimaryKey
    var id: String = ObjectId().toHexString()

    var hasChild: Int? = null
    var name: String = ""
    var icon: String = ""

    constructor(id: String, hasChild: Int?, name: String, icon: String) : this() {
        this.id = id
        this.hasChild = hasChild
        this.name = name
        this.icon = icon
    }
}