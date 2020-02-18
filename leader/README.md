# Ordered Leader Election

N elected officials and one rank thread. Every official thread
created notifies the rank thread which then checks to see the
official rank number to compare to the other current threads. If
a new rank is found the rank notifies all other threads waiting.
