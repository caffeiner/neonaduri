# TIL

## 2022.08.29

### MapReduce Framework

- 맵리듀스 프레임워크에서는 각각의 레코드 또는 튜플은 (KEY, VALUE) 쌍으로 표현됩니다.
- 맵리듀스 프레임워크의 주요 구성 요소는 MapReduce와 HDFS라 불리는 Hadoop 분산 파일 시스템입니다.
- 맵리듀스는 소프트웨어의 수행을 분산하고 HDFS는 데이터를 분산하는 역할을 합니다.

1. 맵 페이즈

- 텍스트 무서의 각 라인 마다 map 함수가 호출되고 문서를 스캔하면서 각 단어마다 그 단어를 KEY로 하고 값 1을 VALUE로 해서 (KEY,VALUE) 쌍을 출력합니다.
- map 함수가 다 끝나고 나면 출력한 (KEY,VALUE) 쌍의 KEY에 따라서 해시 함수 같은 것을 이용해서 여러 머신에 분산하여 보냅니다.
- 여기서 같은 KEY를 가진 (KEY,VALUE) 쌍은 반드시 같은 머신으로 보내집니다.

![image](/uploads/9f1bc40a4db97ed1c6e8394ad0b5f089/image.png)
