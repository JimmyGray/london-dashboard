import React from 'react';
import { Card, CardActions, CardHeader, CardMedia, CardTitle, CardText } from 'material-ui/Card';
import styles from '../common/CardStyles.scss';
import { removeDateFromDateTime } from "../../transform/dateTimeTransformers";


export default class FinanceStatusComponent extends React.Component {

    componentWillReceiveProps() {}

    render() {
        return this.renderCard();
    }

    renderCard() {
        debugger
        return (
            <Card>
                <CardHeader className={'card-header'}>
                    Finance Status
                    {this.renderCardHeaderLastUpdated()}
                </CardHeader>
                <CardText>
                    {this.renderListData()}
                </CardText>
            </Card>
        );
    }

    renderCardHeaderLastUpdated() {
        if (this.props.data && this.props.data.length > 0) {
            return(
                <span className={'card-header-last-checked'}>
                        last updated: {removeDateFromDateTime(this.props.data[0].lastUpdated)}
                </span>
            );
        }
    }

    renderListData() {
        return this.props.data.map((data) => {
            return (<div>
                {data.name}
                {data.price}
                {data.open}
                {data.prevClose}
                {data.change}
            </div>);
        })
    }
};