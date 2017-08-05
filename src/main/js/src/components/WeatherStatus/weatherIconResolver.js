const weatherIconMappings = new Map()
    .set('11d', 'CloudRainThunder.png')
    .set('11n', 'CloudRainThunder.png')
    .set('09d', 'OccLightRain.png')
    .set('09n', 'OccLightRain.png')
    .set('10d', 'HeavyRain.png')
    .set('10n', 'HeavyRain.png')
    .set('13d', 'FreezingRain.png')
    .set('13n', 'FreezingRain.png')
    .set('09d', 'FreezingRain.png')
    .set('09n', 'FreezingRain.png')
    .set('13d', 'HeavySnow.png')
    .set('13n', 'HeavySnow.png')
    .set('50d', 'Mist.png')
    .set('50n', 'Mist.png')
    .set('01d', 'Sunny.png')
    .set('01n', 'Clear.png')
    .set('02d', 'PartlyCloudyDay.png')
    .set('02n', 'PartlyCloudyNight.png')
    .set('03d', 'Cloudy.png')
    .set('03n', 'Cloudy.png')
    .set('04d', 'Overcast.png')
    .set('04n', 'Overcast.png')


export function resolveWeatherIcon(iconId) {
    const weatherIcon = weatherIconMappings.get(iconId);
    return weatherIcon;
}