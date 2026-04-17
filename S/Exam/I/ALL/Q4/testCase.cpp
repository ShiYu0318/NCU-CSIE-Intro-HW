#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
using namespace std;
ofstream fout("output.txt");

class Band
{
private:
    string bandName;

public:
    void input()
    {
        cin >> bandName;
    }

    void display() const
    {
        fout << "Band: " << bandName << endl;
    }

    string getBandName() const
    {
        return bandName;
    }
};

/*
   一場演出：哪個樂團、幾點開始、幾點結束
   時間用整數表示，例如 13 代表 13:00
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
        cin >> bandName;
        cin >> startTime;
        cin >> endTime;
    }

    void display() const
    {
        fout << "Band: " << bandName
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
class Stage
{
private:
    string stageName;
    vector<Performance> performances;

public:
    void input()
    {
        cin >> stageName;
    }

    string getStageName() const
    {
        return stageName;
    }

    void display() const
    {
        fout << "Stage: " << stageName << endl;
    }

    /* TODO:
       檢查新演出是否和這個舞台上既有演出衝突
       只要時間重疊就算衝突，兩區間 [s1, e1) 和 [s2, e2) 不重疊的條件是 e1 <= s2 或 e2 <= s1
    */
    bool hasTimeConflict(const Performance &p) const
    {
        for (const auto &per : performances)
        {
            if (!(per.getEndTime() <= p.getStartTime() ||
                  p.getEndTime() <= per.getStartTime()))
            {
                return true;
            }
        }
        return false;
    }

    // TODO:若沒有衝突則加入演出，否則顯示訊息
    void addPerformance(const Performance &p)
    {
        if (!hasTimeConflict(p))
            performances.push_back(p);
        else
            fout << "Time conflict!" << endl;
    }

    // TODO:依開始時間排序所有演出
    void sortPerformances()
    {
        for (int i = 0; i < performances.size(); i++)
            for (int j = i; j < performances.size(); j++)
                if (performances[i].getStartTime() > performances[j].getStartTime())
                    swap(performances[i], performances[j]);
    }

    void showSchedule()
    {
        if (performances.empty())
        {
            fout << "No performances on this stage.\n";
            return;
        }

        sortPerformances();

        fout << "=== Schedule of " << stageName << " ===" << endl;
        for (const auto &p : performances)
        {
            p.display();
        }
    }

    // TODO: 刪除指定 bandName 的所有演出
    void removePerformancesByBand(string bandName)
    {
        for (int i = performances.size() - 1; i >= 0; i--)
        {
            if (performances[i].getBandName() == bandName)
                performances.erase(performances.begin() + i);
        }
    }

    // TODO: 檢查某樂團是否已在此舞台演出
    bool hasBand(string bandName) const
    {
        for (const auto &p : performances)
        {
            if (p.getBandName() == bandName)
            {
                return true;
            }
        }
        return false;
    }

    // 提供唯讀存取，方便 Festival 檢查跨舞台衝突
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
        cin >> festivalName;
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
            fout << "No stages.\n";
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
            fout << "No bands.\n";
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
    */
    bool hasBandScheduleConflict(const Performance &p, string currentStageName) const
    {
        for (auto s : stages)
        {
            if (s.getStageName() == currentStageName)
                continue;

            if (s.hasBand(p.getBandName()))
            {
                vector<Performance> vec = s.getPerformances();
                for (auto v : vec)
                {
                    if (v.getBandName() == p.getBandName())
                    {
                        if (!(v.getEndTime() <= p.getStartTime() ||
                              p.getEndTime() <= v.getStartTime()))
                        {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /* TODO:
       安排演出
       步驟建議：
       1. 輸入 stageName
       2. 輸入一個 Performance
       3. 檢查 band 是否存在
       4. 檢查 stage 是否存在
       5. 檢查跨舞台同樂團衝突
       6. 呼叫 Stage 的 addPerformance
    */
    void schedulePerformance()
    {
        string stageName;
        cin >> stageName;
        Performance p;
        p.input();
        int stageIdx = findStageIndex(stageName);

        if (findBandIndex(p.getBandName()) == -1)
        {
            fout << "Band not found!\n";
            return;
        }

        if (stageIdx == -1)
        {
            fout << "Stage not found!\n";
            return;
        }

        if (hasBandScheduleConflict(p, stageName))
        {
            fout << "Band schedule conflict!\n";
            return;
        }

        stages[stageIdx].addPerformance(p);
    }

    void showAllSchedules()
    {
        if (stages.empty())
        {
            fout << "No stages.\n";
            return;
        }

        for (auto &s : stages)
        {
            s.showSchedule();
            fout << endl;
        }
    }

    /* TODO:
       刪除樂團，並同步刪除該樂團所有演出
       步驟建議:
       1. 檢查 band 是否存在，如果Band不存在
       2. 先從 bands 中刪除該 band
       3. 再遍歷所有 stages，刪除該樂團的 performances
    */
    void removeBand(string bandName)
    {
        int idx = findBandIndex(bandName);
        if (idx != -1)
        {
            bands.erase(bands.begin() + idx);
            for (auto &s : stages)
            {
                s.removePerformancesByBand(bandName);
            }
        }
        else
        {
            fout << "Band not found!" << endl;
        }
    }
};

int main()
{
    Festival festival;
    int choice;

    while (true)
    {
        cin >> choice;

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
            cin >> bandName;
            festival.removeBand(bandName);
            break;
        }
        default:
            fout << "Invalid choice.\n";
        }
    }

    return 0;
}