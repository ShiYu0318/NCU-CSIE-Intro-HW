#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Vehicle {
protected:
    string vehicleId;
    int minutes;

public:
    Vehicle(string id, int m) : vehicleId(id), minutes(m) {}
    
    virtual ~Vehicle() {}

    string getId() {
        return vehicleId;
    }

    virtual int calculateFee() = 0;
};

class Bike : public Vehicle {
public:
    Bike(string id, int m) : Vehicle(id, m) {}

    int calculateFee() override {
        if (minutes == 0) {
            return 0;
        } else if (minutes <= 5) {
            return 10;
        } else {
            return 10 + (minutes * 1);
        }
    }
};

class Scooter : public Vehicle {
public:
    Scooter(string id, int m) : Vehicle(id, m) {}

    int calculateFee() override {
        if (minutes == 0) {
            return 0;
        } else if (minutes <= 5) {
            return 25;
        } else {
            return 25 + (minutes * 3);
        }
    }
};

int main() {
    int n;
    if (!(cin >> n)) return 0;

    vector<Vehicle*> records;

    for (int i = 0; i < n; i++) {
        char type;
        string id;
        int mins;
        
        cin >> type >> id >> mins;

        if (type == 'B') {
            records.push_back(new Bike(id, mins));
        } else if (type == 'S') {
            records.push_back(new Scooter(id, mins));
        }
    }

    int totalRevenue = 0;

    for (int i = 0; i < records.size(); i++) {
        int fee = records[i]->calculateFee();
        cout << records[i]->getId() << " Fee: " << fee << "\n";
        totalRevenue += fee;
    }

    cout << "Total Revenue: " << totalRevenue << "\n";

    for (int i = 0; i < records.size(); i++) {
        delete records[i];
    }

    return 0;
}