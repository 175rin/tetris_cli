package org.example.model

class PlayerInput(input: Char) {
    var input: Char = input
        set(value) {
            // Ignore invalid input
            if (value in listOf('w', 'a', 's', 'd', 'q', 'e')) {
                field = value
            }
        }
}
