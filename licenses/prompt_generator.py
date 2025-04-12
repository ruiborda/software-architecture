import os

# Lista de archivos a ignorar (puedes añadir nombres o rutas relativas)
IGNORE_FILES = [
    ".gitattributes",
    ".gitignore",
    "mvnw",
    "mvnw.cmd",
    "mvnw.cmd",
    "prompt_generator.py",
    "output.txt",
]

def eliminar_output_si_existe(output_path):
    if os.path.exists(output_path):
        os.remove(output_path)
        print(f"Archivo '{output_path}' eliminado previamente.")

def extraer_texto_de_archivo(file_path):
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            return f.read()
    except Exception as e:
        print(f"Error al leer {file_path}: {e}")
        return f"[Error al leer el archivo: {e}]"

def debe_ignorar(file_path):
    filename = os.path.basename(file_path)
    return filename in IGNORE_FILES

def recorrer_directorio_y_guardar_texto(directorio, output_path):
    with open(output_path, 'w', encoding='utf-8') as output_file:
        for root, dirs, files in os.walk(directorio):
            for file in files:
                file_path = os.path.join(root, file)
                if debe_ignorar(file_path) or file_path == output_path:
                    print(f"Ignorado: {file_path}")
                    continue
                output_file.write(f"=== {file_path} ===\n")
                contenido = extraer_texto_de_archivo(file_path)
                output_file.write(contenido + "\n\n")
                print(f"Procesado: {file_path}")

def main():
    directorio_a_escanear = r"./"
    directorio_output = os.getcwd()
    output_path = os.path.join(directorio_output, "output.txt")

    eliminar_output_si_existe(output_path)
    recorrer_directorio_y_guardar_texto(directorio_a_escanear, output_path)
    print("Proceso completado. Los resultados se han guardado en 'output.txt'.")

if __name__ == "__main__":
    main()
