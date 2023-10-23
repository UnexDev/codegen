fun main() {
    print {
        "my string lol"
    }
}

fun print(block: () -> String) {
    println(block())
}