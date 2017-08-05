import React from 'react';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import { removeDateFromDateTime } from '../../transform/dateTimeTransformers';
import { resolveWeatherIcon } from './weatherIconResolver';

export default class WeatherStatusComponent extends React.Component {

    render() {
        return this.renderCard();
    }

    renderCard() {
        return (
            <Card>
                <CardHeader className={'card-header'}>Weather Status{this.renderCardHeaderLastUpdated()}</CardHeader>
                <CardText>
                    {this.renderListData()}
                </CardText>
            </Card>
        );
    }

    renderCardHeaderLastUpdated() {
        if (this.props.data && this.props.data.length > 0) {
            return (
                <span className={'card-header-last-checked'}>
                        last updated: {removeDateFromDateTime(this.props.data[0].lastUpdated)}
                </span>
            );
        }
    }

    renderListData() {
        return this.props.data.map((data) => {
            console.log(data);
            return (<div>
                <div>{data.name}</div>
                <div>
                    <div>{data.weather[0].description}</div>
                    <img src={require(`./icons/${resolveWeatherIcon(data.weather[0].icon)}`)}></img>
                </div>
                {data.main.temp}
                {data.main.pressure}
                {data.main.humidity}
                {data.main.temp_min}
                {data.main.temp_max}
            </div>);
        })
    }
};

// name:"London"
// ▶main:{} 5 keys
// temp:"271.48"
// pressure:"1026"
// humidity:"83"
// temp_min:"269.15"
// temp_max:"274.15"
