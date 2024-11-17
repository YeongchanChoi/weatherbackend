import os
import json
import argparse
import sys

def collect_java_files(root_dir):
    """
    지정된 루트 디렉토리와 그 하위 디렉토리에서 모든 Java 파일을 찾고, 그 경로와 내용을 수집합니다.

    :param root_dir: Java 파일을 찾을 루트 디렉토리 경로
    :return: Java 파일의 경로와 내용을 포함한 리스트
    """
    java_files = []

    for dirpath, dirnames, filenames in os.walk(root_dir):
        for filename in filenames:
            if filename.endswith(".java"):
                file_path = os.path.join(dirpath, filename)
                try:
                    with open(file_path, 'r', encoding='utf-8') as file:
                        content = file.read()
                    # JSON에 저장하기 위해 경로를 상대 경로로 변환
                    relative_path = os.path.relpath(file_path, root_dir)
                    java_files.append({
                        "path": relative_path.replace(os.sep, '/'),  # 경로 구분자를 '/'로 통일
                        "content": content
                    })
                except Exception as e:
                    print(f"파일을 읽는 중 오류 발생: {file_path}\n오류 내용: {e}", file=sys.stderr)

    return java_files

def save_to_json(data, output_file):
    """
    데이터를 JSON 파일로 저장합니다.

    :param data: 저장할 데이터
    :param output_file: 저장할 JSON 파일 경로
    """
    try:
        with open(output_file, 'w', encoding='utf-8') as json_file:
            json.dump({"files": data}, json_file, ensure_ascii=False, indent=4)
        print(f"모든 Java 파일이 성공적으로 '{output_file}'에 저장되었습니다.")
    except Exception as e:
        print(f"JSON 파일을 저장하는 중 오류 발생: {output_file}\n오류 내용: {e}", file=sys.stderr)

def main():
    # 명령줄 인자 파싱
    parser = argparse.ArgumentParser(description="스프링 부트 프로젝트의 모든 Java 파일을 하나의 JSON 파일에 기록합니다.")
    parser.add_argument(
        "-r", "--root",
        type=str,
        default="src/main/java/com/audora/weatherapp",
        help="Java 파일을 찾을 루트 디렉토리 경로 (기본값: src/main/java/com/audora/weatherapp)"
    )
    parser.add_argument(
        "-o", "--output",
        type=str,
        default="weatherapp_code.json",
        help="출력할 JSON 파일 이름 (기본값: weatherapp_code.json)"
    )

    args = parser.parse_args()

    root_dir = args.root
    output_file = args.output

    if not os.path.isdir(root_dir):
        print(f"지정된 루트 디렉토리가 존재하지 않습니다: {root_dir}", file=sys.stderr)
        sys.exit(1)

    print(f"루트 디렉토리: {root_dir}")
    print(f"출력 파일: {output_file}")
    print("Java 파일을 수집 중입니다...")

    java_files = collect_java_files(root_dir)

    print(f"총 {len(java_files)}개의 Java 파일을 찾았습니다.")
    print("JSON 파일로 저장 중입니다...")

    save_to_json(java_files, output_file)

if __name__ == "__main__":
    main()
