#include <iostream>
#include <thread>
#include <mutex>
#include <chrono>

using namespace std;

/*
    This program manages two threaded counters: one that counts up and another that counts down.
    It demonstrates safe concurrency using mutex locks to avoid interleaved output.
*/
class CounterManager {
private:
    int count;
    mutex mutexLock;

public:
    // Constructor to initialize the maximum count value
    CounterManager(int max) {
        count = max;
    }

    // Counts up from 1 to count
    void countUp() {
        for (int i = 1; i <= count; ++i) {
            lock_guard<mutex> lock(mutexLock);
            cout << "Counting Up: " << i << endl;
        }
    }

    // Counts down from count to 0
    void countDown() {
        for (int i = count; i >= 0; --i) {
            lock_guard<mutex> lock(mutexLock);
            cout << "Counting Down: " << i << endl;
        }
    }
};

int main() {
    const int COUNT = 20;

    // Create a CounterManager object with defined max count
    CounterManager counter(COUNT);

    // "Start thread 1 to count up
    thread thread1(&CounterManager::countUp, &counter);
    // Wait for thread 1 to finish
    thread1.join(); 

    // Start thread 2 to count down
    thread thread2(&CounterManager::countDown, &counter);
    // Wait for thread 2 to finish
    thread2.join(); 

    cout << "Counting complete." << endl;

    return 0;
}
