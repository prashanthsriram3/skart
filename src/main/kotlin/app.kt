import java.math.BigDecimal

data class Product(val name: String, val unitPrice: BigDecimal)

class ShoppingCart(private val items: MutableList<Product> = mutableListOf(),
                   private val taxRate: BigDecimal = BigDecimal("0.00")) {
    var totalTax: BigDecimal = BigDecimal("0.00")
    var total: BigDecimal = BigDecimal("0.00")

    fun addItems(newItems: List<Product>) = items.addAll(newItems)

    fun addItem(item: Product) = items.add(item)

    fun calculateTotal() {
        total = items.fold(BigDecimal("0.00")) { acc, item ->
            acc + item.unitPrice
        }
        totalTax = (total * (taxRate.divide(BigDecimal("100.00")))).setScale(2, BigDecimal.ROUND_HALF_UP)
        total += totalTax
    }
}

fun main(args: Array<String>) {
//    fun ShoppingCart.displayTotal() {
//        println(total)
//    }
//
//    val doveSoaps = (0..4).map { Product("Dove Soap", BigDecimal("39.99")) }
//    val shoppingCart = ShoppingCart(doveSoaps.toMutableList())
//    shoppingCart.items.add(Product("Dove Soap", BigDecimal("39.99")))
//    shoppingCart.calculateTotal()
//    shoppingCart.displayTotal()
}