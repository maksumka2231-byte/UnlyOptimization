import json
import random
import string
from datetime import datetime

def generate_unique_codes(count=150):
    """Генерирует 150 уникальных одноразовых кодов"""
    codes = []
    used_codes = set()
    
    for i in range(1, count + 1):
        # Генерируем уникальный код: UNLY + 4 символа
        while True:
            code = "UNLY" + ''.join(random.choices(string.ascii_uppercase + string.digits, k=4))
            if code not in used_codes:
                used_codes.add(code)
                break
        
        codes.append({
            "code": code,
            "used": False,
            "used_by": None,
            "used_date": None,
            "created_date": datetime.now().isoformat()
        })
    
    return codes

def save_codes_to_file(codes, filename="ActivationCodes/codes.json"):
    """Сохраняет коды в JSON файл"""
    with open(filename, 'w', encoding='utf-8') as f:
        json.dump(codes, f, indent=2, ensure_ascii=False)
    print(f"✅ {len(codes)} кодов сохранено в {filename}")

def generate_codes_list_file(codes, filename="CODES_LIST.txt"):
    """Генерирует текстовый файл со всеми кодами"""
    with open(filename, 'w', encoding='utf-8') as f:
        f.write("=" * 60 + "\n")
        f.write("UNLY OPTIMIZATION - 150 ACTIVATION CODES\n")
        f.write("=" * 60 + "\n")
        f.write(f"Дата создания: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n")
        f.write(f"Всего кодов: {len(codes)}\n")
        f.write("=" * 60 + "\n\n")
        
        for i, code in enumerate(codes, 1):
            f.write(f"{i:3d}. {code['code']}\n")
        
        f.write("\n" + "=" * 60 + "\n")
        f.write("Каждый код одноразовый и навсегда привязывается к пользователю!\n")
        f.write("=" * 60 + "\n")
    
    print(f"✅ Список кодов сохранен в {filename}")

if __name__ == "__main__":
    # Генерируем коды
    print("🔄 Генерирование 150 уникальных кодов активации...")
    codes = generate_unique_codes(150)
    
    # Сохраняем в JSON
    save_codes_to_file(codes)
    
    # Сохраняем список для раздачи
    generate_codes_list_file(codes)
    
    print("\n" + "=" * 60)
    print("✅ КОДЫ УСПЕШНО СГЕНЕРИРОВАНЫ!")
    print("=" * 60)
    print(f"📁 JSON: ActivationCodes/codes.json")
    print(f"📄 Текст: CODES_LIST.txt")
    print("\n🎁 Первые 10 кодов для проверки:")
    for code in codes[:10]:
        print(f"   • {code['code']}")
    print(f"   ... и еще {len(codes) - 10} кодов\n")
