# Data_Acqusition_QC_TableFan is a data acquisition software. It is capable of interacting with different instruments (mentioned below) using serial interface. 
CRC16 (Cyclic Redudancy Error Check) is implemented to reduce the risk of dataloss during transmission. If the data is lost, the software ask for retransmission
from the instruments. For reporting, a soft copy of the QC report is stored locally and hard copy of the report can be printed using printed connected to host 
computer. For data management platform, SQILite is used.

Each instrument transmits encoded data. For example, Power Analyzer sends IEEE single precision floating point format.

Data Collection Instruments: 

1. Power Analyzer(PF9811, Everfine) for Current consumption, Voltage level supplied, and Power (Kw)
2. Techometer(Speed in RPM)
3. Sound level (dB)
4. Temperature information (Celcius)

Communication interfaces used: RS-232
