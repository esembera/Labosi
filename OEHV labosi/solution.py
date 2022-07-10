import matplotlib.pyplot as pp  # plots
import numpy as np              # mat operacije
import scipy.integrate as intg  # za integrale

data = np.loadtxt(fname="data.txt", delimiter='\t')
time = data[:, 0]
current = data[:, 1]
voltage = data[:, 2]
power = data[:, 3]
temperature = data[:, 4]

for i in range(len(time)):
    if current[i] != 0:
        start = i
        break

i = start
while i < len(time):
    if current[i] == 0:
        end = i
        break
    i += 1

print("Trajanje mjerenja: {:0.2f} minuta".format((end - start) / 60))

kapacitet = intg.trapz(current, time / 3600) # Trapezni integral (ako je struja pozitivna maknut -)
print("Kapacitet: {:0.2f} mAh".format(kapacitet * 1000))

energija = intg.trapz(current * voltage, time / 3600) # Trapezna integracija (isto ako je struja pozitivna maknut -)
print("Energija: {:0.2f} Wh".format(energija))

maximum = temperature[0]
minimum = temperature[0]
for i in range(len(temperature)):
    if temperature[i] > maximum:
        maximum = temperature[i]
    if temperature[i] < minimum:
        minimum = temperature[i]
print("Raspon temperatura: od {:0.2f} do {:0.2f}".format(minimum, maximum))

# Struja
pp.figure(1, dpi=150)
pp.xlabel("Time / s")
pp.ylabel("Current / A")
pp.xlim(start, end)
pp.xticks(range(start, end, 1000))
pp.plot(time, current, 'b')

# Napon
pp.figure(2, dpi=150)
pp.xlabel("Time / s")
pp.ylabel("Volage / V")
pp.xlim(start, end)
pp.xticks(range(start, end, 1000))
pp.plot(time, voltage, 'orange')

# Snaga
pp.figure(3, dpi=150)
pp.xlabel("Time / s")
pp.ylabel("Power / W")
pp.xlim(start, end)
pp.xticks(range(start, end, 1000))
pp.plot(time, power, 'g')

# Temperatura
pp.figure(4, dpi=150)
pp.xlabel("Time / s")
pp.ylabel("Temperature / °C")
pp.xlim(start, end)
pp.plot(time, temperature, 'r')
pp.show()