# NCU-CSIE-Intro2CS-HW

中央資工大一計概作業、上機考解法、OOP 實作與自動測試腳本

> 我的解法僅供參考並且不保證為最佳解，複製貼上被助教抓到請自行負責
> 如果有幫助到你歡迎在這個 Repo 右上角點一下 Star 謝謝

- 學年：114
- 課號：CE1002*
- 教師：施國琛

### 資料夾結構

S（下學期）每份 HW 資料夾結構大致如下

```plaintext
HWXX
├── DL  # 存放從 ee-class 下載（DownLoad）的相關檔案
│   ├── 範例程式碼.cpp
│   ├── 範例程式碼.java
│   ├── 標頭檔.h
│   └── 文字讀寫檔.txt
├── UL  # 存放上傳（UpLoad）到 ee-class 和 DOMJudge 的檔案
│   ├── AXX_114502XXX.cpp
│   └── AXX_114502XXX.java
├── output # 存放自動測試腳本 `test.sh` 產出的檔案
│   ├── main    # C++ 編譯執行檔
│   ├── Main.class  # Java 類別檔
│   └── 執行輸出檔.out
├── samples # 存放 DOMJudge 下載的測試資料，每題包含多筆，用於自動化測試
│   ├── 1.in    # 輸入
│   ├── 1.ans   # 答案
│   ├── 2.in
│   └── 2.ans
└── diff.patch  # 用於記錄程式碼的變更，快速比對助教提供的範例程式與我完成的解法相異處
```

## 自動化測試流程

自動編譯 + 自動輸入測資
將輸出與其他檔案存到指定資料夾
比對輸出與答案並輸出 `AC` 或 `WA`（會輸出相異處）

1. 下載 `test.sh` 和 `.env.example` 並改成 `.env`
2. 將以下三個路徑貼進 `.env` 中：
    - FILES: 待編譯檔案路徑
    - SAMPLE_DIR: 測資資料夾路徑
    - OUTPUT_DIR: 存放生成出的 `.out`, `.exe`, `.class` 的資料夾路徑
3. 終端機執行 `./test.sh`
