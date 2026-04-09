Map<String, Integer> map = new HashMap<>();

map.put("apple", 3);
map.put("banana", 5);

int val = map.get("apple");        // 3
int def = map.getOrDefault("grape", 0); // 0 (key missing)

map.containsKey("apple");    // true
map.containsValue(5);        // true
map.isEmpty();               // false
map.size();                  // 2

map.remove("banana");
map.remove("apple", 3);      // removes only if value matches

map.replace("apple", 10);

for (Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

for (String key : map.keySet()) { ... }

for (int val : map.values()) { ... }

map.forEach((k, v) -> System.out.println(k + ": " + v));

// 預設：key 升序排列（自然排序）
TreeMap<String, Integer> map = new TreeMap<>();

// 自訂排序：降序
TreeMap<String, Integer> desc = new TreeMap<>(Comparator.reverseOrder());

// 自訂排序：依字串長度
TreeMap<String, Integer> byLen = new TreeMap<>(Comparator.comparingInt(String::length));

TreeMap<String, Integer> map = new TreeMap<>();
map.put("banana", 2);
map.put("apple", 5);
map.put("cherry", 1);
map.put("date", 3);

// 自動排序 key：{apple=5, banana=2, cherry=1, date=3}

map.get("apple");        // 5
map.containsKey("date"); // true
map.size();              // 4
map.remove("banana");