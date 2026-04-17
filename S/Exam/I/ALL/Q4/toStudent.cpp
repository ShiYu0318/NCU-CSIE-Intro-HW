#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool debugMode = true;

template <typename T>
void inputWithPrompt(const string &prompt, T &value)
{
    if (debugMode)
    {
        cout << prompt;
    }
    cin >> value;
}

class Band
{
private:
    string bandName;

public:
    void input()
    {
        inputWithPrompt("Enter band name: ", bandName);
    }

    void display() const
    {
        cout << "Band: " << bandName << endl;
    }

    string getBandName() const
    {
        return bandName;
    }
};

/*
    一場演出: 哪個樂團、幾點開始、幾點結束
    時間用整數表示，例如 13 代表 13:00
    A performance: which band, start time, and end time
    Time is represented as an integer, e.g., 13 means 13:00
*/
class Performance
{
private:
    string bandName;
    int startTime;
    int endTime;

public:
    void input()
    {
        inputWithPrompt("Enter band name: ", bandName);
        inputWithPrompt("Enter start time: ", startTime);
        inputWithPrompt("Enter end time: ", endTime);
    }

    void display() const
    {
        cout << "Band: " << bandName
             << ", Time: " << startTime
             << ":00 - " << endTime << ":00" << endl;
    }

    string getBandName() const
    {
        return bandName;
    }

    int getStartTime() const
    {
        return startTime;
    }

    int getEndTime() const
    {
        return endTime;
    }
};

// 一個舞台有很多演出
// A stage has multiple performances
class Stage
{
private:
    string stageName;
    vector<Performance> performances;

public:
    void input()
    {
        inputWithPrompt("Enter stage name: ", stageName);
    }

    string getStageName() const
    {
        return stageName;
    }

    void display() const
    {
        cout << "Stage: " << stageName << endl;
    }

    /* TODO:
        檢查新演出是否和這個舞台上既有演出衝突
        只要時間重疊就算衝突，兩區間 [s1, e1) 和 [s2, e2) 不重疊的條件是 e1 <= s2 或 e2 <= s1
        Check whether a new performance conflicts with existing performances on this stage
        Any overlap in time counts as a conflict.
        Two intervals [s1, e1) and [s2, e2) do NOT overlap if: e1 <= s2 OR e2 <= s1
    */
    bool hasTimeConflict(const Performance &p) const
    {
        // TODO
        return false;
    }

    /* TODO:
        若沒有衝突則加入演出，否則顯示訊息 cout << "Time conflict!" << endl;
        If there is no conflict, add the performance; otherwise print: cout << "Time conflict!" << endl;
    */
    void addPerformance(const Performance &p)
    {
        // TODO
    }

    // TODO:依開始時間排序所有演出
    // Sort all performances by start time
    void sortPerformances()
    {
        // TODO
    }

    void showSchedule()
    {
        if (performances.empty())
        {
            cout << "No performances on this stage.\n";
            return;
        }

        sortPerformances();

        cout << "=== Schedule of " << stageName << " ===" << endl;
        for (const auto &p : performances)
        {
            p.display();
        }
    }

    // TODO: 刪除指定 bandName 的所有演出
    // Remove all performances with the specified bandName
    void removePerformancesByBand(string bandName)
    {
        // TODO
    }

    // TODO: 檢查某樂團是否已在此舞台演出
    // Check whether a given band is already performing on this stage
    bool hasBand(string bandName) const
    {
        // TODO
        return false;
    }

    // 提供唯讀存取，方便 Festival 檢查跨舞台衝突
    // Provide read-only access to allow Festival to check cross-stage conflicts
    const vector<Performance> &getPerformances() const
    {
        return performances;
    }
};

class Festival
{
private:
    string festivalName;
    vector<Band> bands;
    vector<Stage> stages;

public:
    void setFestivalName()
    {
        inputWithPrompt("Enter festival name: ", festivalName);
    }

    void addStage()
    {
        Stage s;
        s.input();
        stages.push_back(s);
    }

    void addBand()
    {
        Band b;
        b.input();
        bands.push_back(b);
    }

    void showStages() const
    {
        if (stages.empty())
        {
            cout << "No stages.\n";
            return;
        }

        for (const auto &s : stages)
        {
            s.display();
        }
    }

    void showBands() const
    {
        if (bands.empty())
        {
            cout << "No bands.\n";
            return;
        }

        for (const auto &b : bands)
        {
            b.display();
        }
    }

    int findStageIndex(string stageName) const
    {
        for (int i = 0; i < stages.size(); i++)
        {
            if (stages[i].getStageName() == stageName)
            {
                return i;
            }
        }
        return -1;
    }

    int findBandIndex(string bandName) const
    {
        for (int i = 0; i < bands.size(); i++)
        {
            if (bands[i].getBandName() == bandName)
            {
                return i;
            }
        }
        return -1;
    }

    /* TODO:
        檢查同一樂團是否在其他舞台同時段演出
        若同 band 在其他 stage 的某場演出時間重疊，回傳 true
        Check whether the same band is performing on other stages at overlapping times
        If the same band has a performance on another stage with overlapping time, return true
    */
    bool hasBandScheduleConflict(const Performance &p, string currentStageName) const
    {
        // TODO
        return false;
    }

    /* TODO:
        安排演出
        步驟建議:
        1. 輸入 stageName
        2. 輸入一個 Performance
        3. 檢查 band 是否存在，如果不存在，顯示訊息: cout << "Band not found!\n";
        4. 檢查 stage 是否存在，如果不存在，顯示訊息: cout << "Stage not found!\n";
        5. 檢查跨舞台同樂團衝突，如果衝突，顯示訊息: cout << "Band schedule conflict!\n";
        6. 呼叫 Stage 的 addPerformance
        Schedule a performance
        Suggested steps:
        1. Input stageName
        2. Input a Performance
        3. Check whether the band exists; if not, print: cout << "Band not found!\n";
        4. Check whether the stage exists; if not, print: cout << "Stage not found!\n";
        5. Check cross-stage conflict for the same band; if conflict exists, print: cout << "Band schedule conflict!\n";
        6. Call Stage's addPerformance
    */
    void schedulePerformance()
    {
        string stageName;
        inputWithPrompt("Enter stage name: ", stageName);
        Performance p;
        // TODO
    }

    void showAllSchedules()
    {
        if (stages.empty())
        {
            cout << "No stages.\n";
            return;
        }

        for (auto &s : stages)
        {
            s.showSchedule();
            cout << endl;
        }
    }

    /* TODO:
        刪除樂團，並同步刪除該樂團所有演出
        步驟建議:
        1. 檢查 band 是否存在，如果Band不存在，顯示訊息 cout << "Band not found!" << endl;
        2. 先從 bands 中刪除該 band
        3. 再遍歷所有 stages，刪除該樂團的 performances
        Remove a band and delete all its performances accordingly
        Suggested steps:
        1. Check whether the band exists; if not, print:
        cout << "Band not found!" << endl;
        2. Remove the band from bands
        3. Traverse all stages and remove this band's performances
    */
    void removeBand(string bandName)
    {
        // TODO
    }
};

int main()
{
    Festival festival;
    int choice;

    while (true)
    {
        inputWithPrompt(
            "\n===== Music Festival Scheduling System =====\n"
            "1. Set Festival Name\n"
            "2. Add Band\n"
            "3. Add Stage\n"
            "4. Show All Bands\n"
            "5. Show All Stages\n"
            "6. Schedule Performance\n"
            "7. Show All Schedules\n"
            "8. Remove Band (with performances)\n"
            "0. Exit\n"
            "Choose: ",
            choice);

        if (choice == 0)
            break;

        switch (choice)
        {
        case 1:
            festival.setFestivalName();
            break;
        case 2:
            festival.addBand();
            break;
        case 3:
            festival.addStage();
            break;
        case 4:
            festival.showBands();
            break;
        case 5:
            festival.showStages();
            break;
        case 6:
            festival.schedulePerformance();
            break;
        case 7:
            festival.showAllSchedules();
            break;
        case 8:
        {
            string bandName;
            inputWithPrompt("Enter band name to remove: ", bandName);
            festival.removeBand(bandName);
            break;
        }
        default:
            cout << "Invalid choice.\n";
        }
    }

    return 0;
}