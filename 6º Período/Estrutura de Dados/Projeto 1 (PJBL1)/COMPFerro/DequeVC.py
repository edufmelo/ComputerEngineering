import pickle
import os

class Except(Exception):
    """Tratamento de exceção. Uso: em raise Except(<mensagem>)."""
    pass

class Deque:
    def __init__(self, N):
        self._N = N
        self._data = [None] * N
        self._size = 0
        self._front = 0
        self._top = 0
        self._ptr = 0

    def is_empty(self):
        return self._size == 0

    def is_full(self):
        return self._size == self._N

    def get_size(self):
        return self._size

    def peek(self):
        if self.is_empty():
            return None
        return self._data[self._front]

    def top(self):
        if self.is_empty():
            return None
        return self._data[self._top]

    def _str_(self):
        if self.is_empty():
            return "Deque vazio."
        lista_temp = []
        self.rewind()
        for _ in range(self._size):
            lista_temp.append(self.next())
        return str(lista_temp)

    def getVC(self):
        if self.is_empty():
            return "Vetor vazio."
        return str(self._data)

    def rewind(self):
        self._ptr = self._front

    def next(self):
        if self.is_empty():
            return None
        e = self._data[self._ptr]
        self._ptr = (self._ptr + 1) % self._N
        return e

    def addFirst(self, e):
        if self.is_full():
            raise Except("Deque cheio!")
        self._front = (self._front - 1) % self._N
        self._data[self._front] = e
        self._size += 1

    def addLast(self, e):
        if self.is_full():
            raise Except("Deque cheio!")
        self._data[self._top] = e
        self._top = (self._top + 1) % self._N
        self._size += 1

    def deleteFirst(self):
        if self.is_empty():
            raise Except("Deque vazio!")
        e = self._data[self._front]
        self._data[self._front] = None
        self._front = (self._front + 1) % self._N
        self._size -= 1
        return e

    def deleteLast(self):
        if self.is_empty():
            raise Except("Deque vazio!")
        self._top = (self._top - 1) % self._N
        e = self._data[self._top]
        self._data[self._top] = None
        self._size -= 1
        return e

